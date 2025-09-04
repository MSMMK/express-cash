package org.example.expresscash.repositories;

import org.example.expresscash.constants.UserTypeEnum;
import org.example.expresscash.entity.UserTypeLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserTypeLookupRepository extends JpaRepository<UserTypeLookup, Long> {

    Optional<UserTypeLookup> findByCode(UserTypeEnum typeEnum);
}
