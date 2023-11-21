package com.megacoffee.OrderApp.dto;

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
    private String itemImgUrl;
    private long itemPrice;
    private long cartItemAmount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime cartDate;

    private static CartDto toDto(CartDto entity){
        return CartDto.builder()
                .cartNo(entity.getCartNo())
                .cartCode(entity.getCartCode())
                .itemCode(entity.getItemCode())
                .itemName(entity.getItemCode())
                .itemImgUrl(entity.getItemImgUrl())
                .itemPrice(entity.getItemPrice())
                .cartItemAmount(entity.getCartItemAmount())
                .cartDate(entity.getCartDate())
                .build();
    }


}
