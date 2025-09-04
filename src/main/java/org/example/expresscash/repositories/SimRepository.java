package org.example.expresscash.repositories;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.example.expresscash.entity.QSim;
import org.example.expresscash.entity.Sim;
import org.example.expresscash.model.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SimRepository extends JpaRepository<Sim, Long> {

    List<Sim> findAllByBranchId(Long branchId);

    default Page<Sim> search(SearchCriteria searchCriteria, Pageable pageable, EntityManager entityManager){
        QSim sim = QSim.sim;

        BooleanBuilder where = new BooleanBuilder();
        if(searchCriteria.getBranchId() != null) where.and(sim.branch.id.eq(searchCriteria.getBranchId()));
        if(searchCriteria.getUserId() != null) where.and(sim.customer.id.eq(searchCriteria.getUserId()));
        if(searchCriteria.isAdmin()) {
            where.and(sim.customer.isNull());
        } else  {
            where.and(sim.customer.isNotNull());
        }

        JPAQuery<Sim> query = new JPAQuery<>(entityManager);
        query.where(where)
                .from(sim)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(query.stream().toList());
    }
}
