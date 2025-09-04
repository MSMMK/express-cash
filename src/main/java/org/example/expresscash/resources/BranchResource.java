package org.example.expresscash.resources;

import lombok.RequiredArgsConstructor;
import org.example.expresscash.model.BranchModel;
import org.example.expresscash.model.CustomerModel;
import org.example.expresscash.model.SearchCriteria;
import org.example.expresscash.services.BranchService;
import org.example.expresscash.services.CustomerService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("branches")
@RequiredArgsConstructor
public class BranchResource {
    private final BranchService branchService;
    private final CustomerService customerService;

    @PostMapping
    public void create(@RequestBody BranchModel model){
        branchService.addBranch(model);
    }

    @PostMapping("search")
//    @PreAuthorize("hasRole('ADMIN')")
    public List<BranchModel> list(@RequestBody SearchCriteria searchCriteria, Pageable pageable) {
        return branchService.search(searchCriteria, pageable);
    }

    @GetMapping("{id}/customers")
    public List<CustomerModel> list(@PathVariable Long id, @RequestParam String type) {
        return customerService.listCustomersByBranchId(id, type);
    }


    @DeleteMapping("{code}")
    public void delete(@PathVariable String code) {
        branchService.deleteBranch(code);
    }


}
