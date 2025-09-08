package org.example.expresscash.services;

import org.example.expresscash.constants.TransactionTypeEnum;
import org.example.expresscash.model.SearchCriteria;
import org.example.expresscash.model.SimModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SimService {

    void addSim(SimModel simModel);

    void delete(Long userId, Long simId);

    void updateSim(SimModel model, Double amount, TransactionTypeEnum type);

    List<SimModel> listAllByBranchId(Long branchId);

    List<SimModel> listAll();

    List<SimModel> search(SearchCriteria searchCriteria, Pageable pageable);
}
