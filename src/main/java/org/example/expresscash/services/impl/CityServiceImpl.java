package org.example.expresscash.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.expresscash.constants.StatusCodeEnum;
import org.example.expresscash.entity.City;
import org.example.expresscash.exceptions.BusinessException;
import org.example.expresscash.mappers.LookupsMapper;
import org.example.expresscash.model.LookupModel;
import org.example.expresscash.repositories.CityRepository;
import org.example.expresscash.services.CityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final LookupsMapper lookupsMapper;

//    @Cacheable(value= "city", key = "#id")
    @Override
    public LookupModel findById(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new BusinessException(StatusCodeEnum.CITY_NOT_FOUND, "City with id {} not found", id));
        return lookupsMapper.toCityModel(city);
    }

    @Override
    public List<LookupModel> listByGovId(Long govId) {
        return cityRepository.findAllByGovId(govId)
                .stream()
                .map(lookupsMapper::toCityModel)
                .collect(Collectors.toList());
    }
}
