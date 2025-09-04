package org.example.expresscash.resources;

import lombok.RequiredArgsConstructor;
import org.example.expresscash.model.BranchModel;
import org.example.expresscash.model.CustomerModel;
import org.example.expresscash.model.SearchCriteria;
import org.example.expresscash.services.BranchService;
import org.example.expresscash.services.CustomerService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerResource {
    private final CustomerService customerService;

    @PostMapping
    public void create(@RequestBody CustomerModel model){
        customerService.addCustomer(model);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }


}
