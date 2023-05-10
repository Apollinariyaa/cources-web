package com.example.airport.repos;

import com.example.airport.domain.ShopElement;
import org.springframework.data.repository.CrudRepository;

public interface ShopElementRepo extends CrudRepository<ShopElement, Long> {
    Iterable<ShopElement> findByCountInComplect(int countInComplect);
    Iterable<ShopElement> findByCountInComplectGreaterThan(int countInComplect);
}
