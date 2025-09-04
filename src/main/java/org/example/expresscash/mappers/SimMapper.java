package org.example.expresscash.mappers;

import org.example.expresscash.entity.Sim;
import org.example.expresscash.model.SimModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {BranchMapper.class, LookupsMapper.class, CustomerMapper.class})
public interface SimMapper {
    @Mapping(target = "status", source = "status.code")
    SimModel toModel(Sim sim);

    @Mapping(target = "status", source = "status", ignore = true)
    Sim toEntity(SimModel simModel);

}
