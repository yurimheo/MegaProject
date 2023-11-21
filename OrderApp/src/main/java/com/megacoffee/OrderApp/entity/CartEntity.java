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

    public CartDto toDto() {
        return CartDto.builder()
                .cartNo(this.getCartNo())
                .cartCode(this.getCartCode())
                .itemCode(this.getItemCode())
                .itemName(this.getItemName())
                .itemImageUrl(this.getItemImageUrl()) // 수정된 부분
                .itemPrice(this.getItemPrice())
                .cartItemAmount(this.getCartItemAmount())
                .cartDate(this.getCartDate())
                .build();
    }
}
