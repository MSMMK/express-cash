package org.example.expresscash.repositories;

import org.example.expresscash.constants.TransactionTypeEnum;
import org.example.expresscash.entity.TransactionTypeLookup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionTypeRepository extends JpaRepository<TransactionTypeLookup, Long> {
    Optional<TransactionTypeLookup> findByCode(String transactionType);
}
