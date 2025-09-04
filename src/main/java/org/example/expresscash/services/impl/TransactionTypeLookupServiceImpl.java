package org.example.expresscash.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.expresscash.mappers.LookupsMapper;
import org.example.expresscash.model.LookupModel;
import org.example.expresscash.repositories.TransactionTypeRepository;
import org.example.expresscash.services.TransactionTypeLookupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class TransactionTypeLookupServiceImpl implements TransactionTypeLookupService {
    private final TransactionTypeRepository transactionTypeRepository;
    private final LookupsMapper lookupsMapper;
    @Override
    public List<LookupModel> listAll() {
        return transactionTypeRepository.findAll()
                .stream()
                .map(lookupsMapper::toTransactionType)
                .collect(Collectors.toList());
    }
}
