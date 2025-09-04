package org.example.expresscash.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.expresscash.entity.Branch;
import org.example.expresscash.entity.Customer;
import org.example.expresscash.entity.Sim;
import org.example.expresscash.entity.Status;
import org.example.expresscash.mappers.CustomerMapper;
import org.example.expresscash.mappers.SimMapper;
import org.example.expresscash.model.CustomerModel;
import org.example.expresscash.model.SimModel;
import org.example.expresscash.repositories.BranchRepository;
import org.example.expresscash.repositories.CustomerRepository;
import org.example.expresscash.repositories.SimRepository;
import org.example.expresscash.repositories.StatusRepository;
import org.example.expresscash.services.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final StatusRepository statusRepository;
    private final SimMapper simMapper;
    private final SimRepository simRepository;
    private final BranchRepository branchRepository;
    @Override
    public void addCustomer(CustomerModel customerModel) {
        Customer customer = customerMapper.toEntity(customerModel);
        Status status = statusRepository.findByCode(customerModel.getStatus()).orElse(null);
        Branch branch = branchRepository.findByCode(customerModel.getBranch().getCode()).orElse(null);
        customer.setStatus(status);
        customer.setBranch(branch);
        customer.setCif(String.join("", customerModel.getCif().split("-")));
        customer = customerRepository.saveAndFlush(customer);
        Customer finalCustomer = customer;
        Set<Sim> sims = customerModel.getSimNumbers()
                .stream()
                .map(sim -> SimModel.builder().phoneNumber(sim).build())
                .map(simMapper::toEntity)
                .peek(sim ->  setSimDetails(sim, finalCustomer, branch, status))
                .collect(Collectors.toSet());
        simRepository.saveAll(sims);
    }

    private void setSimDetails(Sim sim, final Customer customer, Branch branch, Status status) {
        sim.setBranch(branch);
        sim.setCustomer(customer);
        sim.setStatus(status);
    }
    @Override
    public List<CustomerModel> listCustomersByBranchId(Long branchId, String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Type must not be null or empty");
        }
        List<Customer> customers;
        if (type.equals("ADMIN")) {
            customers = customerRepository.findAll();
        }else {
            customers = customerRepository.findAllByBranchId(branchId);
        }

        return customers
                .stream()
                .map(customerMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
