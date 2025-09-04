package org.example.expresscash.repositories;

import org.example.expresscash.constants.StatusEnum;
import org.example.expresscash.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Long> {


    Optional<Status> findByCode(StatusEnum code);
}
