package com.megacoffee.OrderApp.dto;

import com.megacoffee.OrderApp.entity.CartEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDateTime;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private long cartNo;
    private String cartCode;
    private String itemCode;
    private String itemName;
    private String itemImageUrl;
    private long itemPrice;
    private long cartItemAmount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime cartDate;

    public static CartDto fromEntity(CartEntity entity){
        return CartDto.builder()
                .cartNo(entity.getCartNo())
                .cartCode(entity.getCartCode())
                .itemCode(entity.getItemCode())
                .itemName(entity.getItemName())
                .itemImageUrl(entity.getItemImageUrl())
                .itemPrice(entity.getItemPrice())
                .cartItemAmount(entity.getCartItemAmount())
                .cartDate(entity.getCartDate())
                .build();
    }
}




