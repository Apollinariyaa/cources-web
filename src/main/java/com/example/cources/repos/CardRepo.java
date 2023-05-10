package com.example.cources.repos;

import com.example.cources.domain.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepo extends CrudRepository<Card, Long> {
    List<Card> findAllByClientInfo_Id(Long id);
}
