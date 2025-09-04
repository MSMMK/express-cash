package org.example.expresscash.mappers;

import org.example.expresscash.entity.Branch;
import org.example.expresscash.model.BranchModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = LookupsMapper.class)
public interface BranchMapper {

    Branch toEntity(BranchModel model);
    BranchModel toModel(Branch branch);
}
