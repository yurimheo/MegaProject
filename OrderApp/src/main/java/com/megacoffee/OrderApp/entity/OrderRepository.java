package com.megacoffee.OrderApp.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {

    void deleteByOrderNo(int orderNo);



    List<OrderEntity> findByOrderNo(int searchIntValue);

    List<OrderEntity> findByMemberIdContaining(String searchValue);
}
