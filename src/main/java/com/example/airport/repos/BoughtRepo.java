package com.example.airport.repos;

import com.example.airport.domain.Bought;
import org.springframework.data.repository.CrudRepository;

public interface BoughtRepo extends CrudRepository<Bought, Long> {
}
