package org.example.expresscash.repositories;

import org.example.expresscash.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


    List<Customer> findAllByBranchId(Long branchId);
}
