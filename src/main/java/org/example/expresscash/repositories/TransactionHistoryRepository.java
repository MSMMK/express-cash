package org.example.expresscash.repositories;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.example.expresscash.entity.QTransactionHistory;
import org.example.expresscash.entity.TransactionHistory;
import org.example.expresscash.model.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import static com.querydsl.core.types.dsl.Expressions.TRUE;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
    default Page<TransactionHistory> search(SearchCriteria searchCriteria, EntityManager entityManager, Pageable pageable) {
        QTransactionHistory transactionHistory = QTransactionHistory.transactionHistory;

        BooleanBuilder where = new BooleanBuilder();
        if(searchCriteria.getSimId() != null) where.and(TRUE).and(transactionHistory.sim.id.eq(searchCriteria.getSimId()));
        if(searchCriteria.getUserId() != null) where.and(transactionHistory.user.id.eq(searchCriteria.getUserId()));
        if(searchCriteria.getBranchId() != null) where.and(transactionHistory.branch.id.eq(searchCriteria.getBranchId()));

        JPAQuery<TransactionHistory> query = new JPAQuery<>(entityManager);
        query.from(transactionHistory)
                .leftJoin(transactionHistory.user)
                .where(where)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(query.stream().toList());
    }
}
