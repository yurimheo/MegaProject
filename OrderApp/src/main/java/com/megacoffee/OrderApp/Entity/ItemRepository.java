package com.megacoffee.OrderApp.entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    void deleteByItemName(String itemName);


    List<ItemEntity> findByItemNew(int searchIntValue);

    @Query("SELECT i FROM ItemEntity i WHERE i.itemCate = '커피' AND i.itemName LIKE %:searchValue%")
    List<ItemEntity> findCoffeeItemsByNameContaining(@Param("searchValue") String searchValue);

    @Query("SELECT i FROM ItemEntity i WHERE i.itemCate = '에이드&스무디' AND i.itemName LIKE %:searchValue%")
    List<ItemEntity> findAdeAndSmoothieItemsByNameContaining(@Param("searchValue") String searchValue);

    @Query("SELECT i FROM ItemEntity i WHERE i.itemCate = '티' AND i.itemName LIKE %:searchValue%")
    List<ItemEntity> findTeaItemsByNameContaining(@Param("searchValue") String searchValue);

    List<ItemEntity> findByItemNameContaining(String searchValue);

    Optional<ItemEntity> findOptionalByItemName(String name);
    List<ItemEntity> findByItemName(String name);

    List<ItemEntity> findByItemCate(String category);

    List<ItemEntity> findByItemCode(String code);

    List<ItemEntity> findByItemNo(long no);
}
