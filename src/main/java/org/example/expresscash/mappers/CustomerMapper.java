package org.example.expresscash.mappers;

import org.example.expresscash.entity.Customer;
import org.example.expresscash.model.CustomerModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = BranchMapper.class)
public interface CustomerMapper {

    @Mapping(source = "status.code", target = "status")
    CustomerModel toModel(Customer customer);

    @Mapping(source = "status", target = "status", ignore = true)
    Customer toEntity(CustomerModel model);
}
