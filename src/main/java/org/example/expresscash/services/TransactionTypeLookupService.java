package org.example.expresscash.services;

import org.example.expresscash.model.LookupModel;

import java.util.List;

public interface TransactionTypeLookupService {

    List<LookupModel> listAll();
}
