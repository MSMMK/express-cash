package org.example.expresscash.services.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.expresscash.constants.StatusCodeEnum;
import org.example.expresscash.constants.UserTypeEnum;
import org.example.expresscash.entity.TransactionHistory;
import org.example.expresscash.exceptions.BusinessException;
import org.example.expresscash.mappers.TransactionHistoryMapper;
import org.example.expresscash.model.SearchCriteria;
import org.example.expresscash.model.TransactionHistoryModel;
import org.example.expresscash.repositories.TransactionHistoryRepository;
import org.example.expresscash.repositories.TransactionTypeRepository;
import org.example.expresscash.services.AuthService;
import org.example.expresscash.services.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionHistoryServiceImpl implements TransactionHistoryService {
    private final TransactionHistoryRepository transactionHistoryRepository;
    private final TransactionTypeRepository transactionTypeRepository;
    private final TransactionHistoryMapper transactionHistoryMapper;
    private final AuthService authService;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createTransaction(TransactionHistoryModel model) {
        TransactionHistory history = transactionHistoryMapper.toEntity(model);
        history.setTransactionType(transactionTypeRepository.findByCode(model.getTransactionType().name()).orElse(null));
        history.setUser(authService.authUser());
        history.setCreationDate(LocalDateTime.now());
        history.setLastModificationDate(LocalDateTime.now());
        transactionHistoryRepository.save(history);
    }

    @Override
    public List<TransactionHistoryModel> listTransactions(SearchCriteria searchCriteria, Pageable pageable) {
        return transactionHistoryRepository.search(searchCriteria, entityManager, pageable)
                .getContent()
                .stream()
                .map(transactionHistoryMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        transactionHistoryRepository.findById(transactionId)
                .map(transactionHistory ->  {
                    if (transactionHistory.getUser().getUserType().getCode().equals(UserTypeEnum.ADMIN) || authService.authUser().getId().equals(transactionHistory.getUser().getId())){
                        transactionHistoryRepository.deleteById(transactionId);
                    }
                    return transactionHistory;
                }).orElseThrow(() -> new BusinessException(StatusCodeEnum.USER_NOT_ALLOWED, "user not allowed to delete this transaction"));
    }
}
