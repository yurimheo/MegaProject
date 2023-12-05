package com.megacoffee.OrderApp.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
    List<StoreEntity> findByStoreName(String storeName);

    List<StoreEntity> findByStoreNameContaining(String searchValue);
}
