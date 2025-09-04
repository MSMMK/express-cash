package org.example.expresscash.mappers;

import org.example.expresscash.entity.TransactionHistory;
import org.example.expresscash.model.TransactionHistoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SimMapper.class, CustomerMapper.class, UserMapper.class})
public interface TransactionHistoryMapper {

    @Mapping(source = "transactionType.code", target = "transactionType")
    TransactionHistoryModel toModel(TransactionHistory transactionHistory);

    @Mapping(source = "transactionType", target = "transactionType", ignore = true)
    TransactionHistory toEntity(TransactionHistoryModel model);
}
