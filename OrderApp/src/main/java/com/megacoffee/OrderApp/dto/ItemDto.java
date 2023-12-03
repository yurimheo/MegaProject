package com.megacoffee.OrderApp.dto;

import com.megacoffee.OrderApp.entity.ItemEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private Long itemNo;
    private String itemCode;
    private String itemName;
    private String itemCate;
    private Integer itemPrice;
    private Integer itemRecommend;
    private Integer itemNew;
    private String itemImageUrl;
    private String itemExplanation; // 상품 설명
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime itemUpdateDatetime;


    public static ItemDto fromEntity(ItemEntity entity){
        return ItemDto.builder()
                .itemNo(entity.getItemNo())
                .itemCode(entity.getItemCode())
                .itemName(entity.getItemName())
                .itemCate(entity.getItemCate())
                .itemPrice(entity.getItemPrice())
                .itemRecommend(entity.getItemRecommend())
                .itemNew(entity.getItemNew())
                .itemImageUrl(entity.getItemImageUrl())
                .itemExplanation(entity.getItemExplanation())
                .itemUpdateDatetime(entity.getItemUpdateDatetime())
                .build();
    }

    public static ItemDto toDto(ItemEntity entity){
        return ItemDto.builder()
                .itemNo(entity.getItemNo())
                .itemCode(entity.getItemCode())
                .itemName(entity.getItemName())
                .itemCate(entity.getItemCate())
                .itemPrice(entity.getItemPrice())
                .itemRecommend(entity.getItemRecommend())
                .itemNew(entity.getItemNew())
                .itemImageUrl(entity.getItemImageUrl())
                .itemExplanation(entity.getItemExplanation())
                .itemUpdateDatetime(entity.getItemUpdateDatetime())
                .build();
    }


}