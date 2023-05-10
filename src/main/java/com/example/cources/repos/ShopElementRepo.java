package com.example.cources.repos;

import com.example.cources.domain.ShopElement;
import org.springframework.data.repository.CrudRepository;

public interface ShopElementRepo extends CrudRepository<ShopElement, Long> {
    Iterable<ShopElement> findByCountInComplect(int countInComplect);
    Iterable<ShopElement> findByCountInComplectGreaterThan(int countInComplect);
}
