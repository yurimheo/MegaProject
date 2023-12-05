package com.megacoffee.OrderApp.dto;

import com.megacoffee.OrderApp.entity.ItemEntity;
import com.megacoffee.OrderApp.entity.StoreEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {
    private Long storeNo;   //고유키
    private String storeLocal;  //지역
    private String storeName;   //지점명
    private String storeAddress;    //지점주소

    public static StoreDto toDto(StoreEntity entity){
        return StoreDto.builder()
                .storeNo(entity.getStoreNo())
                .storeLocal(entity.getStoreLocal())
                .storeName(entity.getStoreName())
                .storeAddress(entity.getStoreAddress())
                .build();

    }
}
