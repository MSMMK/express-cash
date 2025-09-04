package org.example.expresscash.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.expresscash.mappers.LookupsMapper;
import org.example.expresscash.model.LookupModel;
import org.example.expresscash.repositories.UserTypeLookupRepository;
import org.example.expresscash.services.UserTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserTypeServiceImpl implements UserTypeService {

    private final UserTypeLookupRepository userTypeLookupRepository;
    private final LookupsMapper lookupsMapper;

    @Override
    public List<LookupModel> list() {
        return userTypeLookupRepository.findAll()
                .stream()
                .map(lookupsMapper::toUserTypeModel)
                .collect(Collectors.toList());
    }
}
