package com.megacoffee.OrderApp.Entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    void deleteByItemName(String itemName);



}
