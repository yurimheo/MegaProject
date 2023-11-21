package com.megacoffee.OrderApp.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private long orderNo;
    private String cartItemCode1;
    private String cartItemCode2;
    private String cartItemCode3;
    private String cartItemCode4;
    private String cartItemCode5;

    private long orderTotalPrice;
    private long orderTotalCount;
    private long orderNumber;
    private long orderPayType;
    private String orderState;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime orderDatetime;

    public static OrderDto todto(OrderDto entity){
        return OrderDto.builder()
                .orderNo(entity.getOrderNo())
                .cartItemCode1(entity.getCartItemCode1())
                .cartItemCode2(entity.getCartItemCode2())
                .cartItemCode3(entity.getCartItemCode3())
                .cartItemCode4(entity.getCartItemCode4())
                .cartItemCode5(entity.getCartItemCode5())
                .orderTotalPrice(entity.getOrderTotalPrice())
                .orderTotalCount(entity.getOrderTotalCount())
                .orderNumber(entity.getOrderNumber())
                .orderPayType(entity.getOrderPayType())
                .orderState(entity.getOrderState())
                .orderDatetime(entity.getOrderDatetime())
                .build();
    }

}
