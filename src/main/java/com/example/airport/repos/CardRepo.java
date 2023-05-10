package com.example.airport.repos;

import com.example.airport.domain.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepo extends CrudRepository<Card, Long> {
    List<Card> findAllByClientInfo_Id(Long id);
}
