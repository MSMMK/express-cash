package org.example.expresscash.repositories;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.example.expresscash.entity.QUser;
import org.example.expresscash.entity.User;
import org.example.expresscash.model.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);

    default Page<User> search(SearchCriteria searchCriteria, Long userId, Pageable pageable, EntityManager entityManager){
        QUser user = QUser.user;

        BooleanBuilder where = new BooleanBuilder();
        if(searchCriteria.getQuery() != null) where.and(user.username.eq(searchCriteria.getQuery()));
        if(searchCriteria.getUserId() != null) where.and(user.id.eq(searchCriteria.getUserId()));
        if(user.superUser != null) where.and(user.superUser.id.eq(userId));

        JPAQuery<User> query = new JPAQuery<>(entityManager);
        query.where(where)
                .from(user)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(query.stream().toList());
    }
}
