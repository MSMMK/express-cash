package org.example.expresscash.services;

import org.example.expresscash.model.BranchModel;
import org.example.expresscash.model.SearchCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BranchService {

    void addBranch(BranchModel branchModel);


    List<BranchModel> search(SearchCriteria searchCriteria, Pageable pageable);

    void deleteBranch(String branchCode);
}
