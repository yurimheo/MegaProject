package com.megacoffee.OrderApp.entity;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<CartEntity, Long> {


    void deleteByItemName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE CartEntity c SET c.cartItemAmount = :amount WHERE c.itemName = :itemName")
    void updateItemAmount(String itemName, int amount);
}
