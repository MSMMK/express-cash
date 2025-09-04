package org.example.expresscash.repositories;

import org.example.expresscash.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findAllByGovId(Long govId);
}
