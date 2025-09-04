package org.example.expresscash.services;

import org.example.expresscash.model.LookupModel;

import java.util.List;

public interface GovernorateService {
    LookupModel findById(Long id);

    List<LookupModel> findAll();

}
