package com.megacoffee.OrderApp.Entity;

import com.megacoffee.OrderApp.dto.ItemDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name="item")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_no")
    private Long itemNo;
    @Column(name = "item_code")
    private String itemCode;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "item_cate")
    private String itemCate;
    @Column(name = "item_price")
    private Integer itemPrice;
    @Column(name = "item_recommend")
    private Integer itemRecommend;
    @Column(name = "item_new")
    private Integer itemNew;
    @Column(name = "item_image_url")
    private String itemImageUrl;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime itemUpdateDatetime;

    public static ItemEntity toEntity(ItemDto dto){
        return ItemEntity.builder()
                .itemNo(dto.getItemNo())
                .itemCode(dto.getItemCode())
                .itemName(dto.getItemName())
                .itemCate(dto.getItemCate())
                .itemPrice(dto.getItemPrice())
                .itemRecommend(dto.getItemRecommend())
                .itemNew(dto.getItemNew())
                .itemImageUrl(dto.getItemImageUrl())
                .itemUpdateDatetime(dto.getItemUpdateDatetime())
                .build();
    }


}
