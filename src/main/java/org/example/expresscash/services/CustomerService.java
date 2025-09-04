package org.example.expresscash.services;

import org.example.expresscash.model.CustomerModel;

import java.util.List;

public interface CustomerService {

    void addCustomer(CustomerModel customerModel);

    List<CustomerModel> listCustomersByBranchId(Long branchId, String type);

    void deleteCustomer(Long customerId);


}
