package com.megacoffee.OrderApp.entity;


import com.megacoffee.OrderApp.dto.CartDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name="cart")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_no")
    private long cartNo;
    @Column(name = "cart_code")
    private String cartCode;
    @Column(name = "item_code")
    private String itemCode;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "item_image_url")
    private String itemImageUrl;
    @Column(name = "item_price")
    private long itemPrice;
    @Column(name = "cart_item_amount")
    private long cartItemAmount;
    @Column(name = "cart_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime cartDate;

    public static CartEntity toCartEntity(CartDto dto) {
        return CartEntity.builder()
                .cartNo(dto.getCartNo())
                .cartCode(dto.getCartCode())
                .itemCode(dto.getItemCode())
                .itemName(dto.getItemName())
                .itemImageUrl(dto.getItemImageUrl()) // 수정된 부분
                .itemPrice(dto.getItemPrice())
                .cartItemAmount(dto.getCartItemAmount())
                .cartDate(dto.getCartDate())
                .build();
    }

    public static CartEntity toEntity(CartDto dto) {
        return CartEntity.builder()
                .cartNo(dto.getCartNo())
                .cartCode(dto.getCartCode())
                .itemCode(dto.getItemCode())
                .itemName(dto.getItemName())
                .itemImageUrl(dto.getItemImageUrl()) // 수정된 부분
                .itemPrice(dto.getItemPrice())
                .cartItemAmount(dto.getCartItemAmount())
                .cartDate(dto.getCartDate())
                .build();
    }
}
