package com.megacoffee.OrderApp.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {

    void deleteByOrderNo(long orderNo);



    List<OrderEntity> findByOrderNo(long searchIntValue);

    List<OrderEntity> findByMemberIdContaining(String searchValue);

    OrderEntity save(OrderEntity orderEntity);


}
