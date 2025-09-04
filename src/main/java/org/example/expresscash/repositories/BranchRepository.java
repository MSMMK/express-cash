package org.example.expresscash.repositories;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.example.expresscash.entity.Branch;
import org.example.expresscash.entity.QBranch;
import org.example.expresscash.model.BranchModel;
import org.example.expresscash.model.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, Long> {
     Optional<Branch> findByCode(String code);

    default Page<BranchModel> search(SearchCriteria searchCriteria, EntityManager entityManager, Pageable pageable) {
        QBranch branch = QBranch.branch;
        BooleanBuilder where = new BooleanBuilder();
        if (searchCriteria.getQuery() != null) where.and(branch.nameEn.eq(searchCriteria.getQuery()).or(branch.nameAr.eq(searchCriteria.getQuery())));
        if (searchCriteria.getUserId() != null) where.and(branch.userId.eq(searchCriteria.getUserId()));

        JPAQuery<BranchModel> query = new JPAQuery<>(entityManager);
        query.where(where)
                .from(branch)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(query.stream().toList());
    }
}
