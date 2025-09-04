package org.example.expresscash.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.expresscash.constants.StatusCodeEnum;
import org.example.expresscash.entity.Governorate;
import org.example.expresscash.exceptions.BusinessException;
import org.example.expresscash.mappers.LookupsMapper;
import org.example.expresscash.model.LookupModel;
import org.example.expresscash.repositories.GovernorateRepository;
import org.example.expresscash.services.GovernorateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GovernorateServiceImpl implements GovernorateService {

    private final GovernorateRepository governorateRepository;
    private final LookupsMapper lookupsMapper;
//    @Cacheable(value = "governorates", key = "#id")
    @Override
    public LookupModel findById(Long id) {
       Governorate governorate = governorateRepository.findById(id)
               .orElseThrow(() -> new BusinessException(StatusCodeEnum.GOVERNORATE_NOT_FOUND, "governorate with id {} not found", id));
        return lookupsMapper.toGovernorateModel(governorate);
    }

    @Override
    public List<LookupModel> findAll() {
        return governorateRepository.findAll()
                .stream()
                .map(lookupsMapper::toGovernorateModel)
                .collect(Collectors.toList());
    }
}
