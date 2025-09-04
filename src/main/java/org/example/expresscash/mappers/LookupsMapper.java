package org.example.expresscash.mappers;

import org.example.expresscash.entity.*;
import org.example.expresscash.model.LookupModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LookupsMapper {

    City toCityEntity(LookupModel cityModel);

    LookupModel toCityModel(City city);

    Governorate toGovEntity(LookupModel govModel);

    LookupModel toGovernorateModel(Governorate governorate);

    LookupModel toUserTypeModel(UserTypeLookup userTypeLookup);

    LookupModel toStatusModel(Status status);
    Status toStatusEntity(LookupModel model);

    LookupModel toTransactionType(TransactionTypeLookup transactionTypeLookup);
}
