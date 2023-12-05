package com.megacoffee.OrderApp.entity;


import com.megacoffee.OrderApp.dto.ItemDto;
import com.megacoffee.OrderApp.dto.StoreDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="store")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_no")      //고유키
    private Long storeNo;
    @Column(name = "store_local")   //지역
    private String storeLocal;
    @Column(name = "store_name")    //지점명
    private String storeName;
    @Column(name = "store_address") //지점주소
    private String storeAddress;

    public static StoreEntity toEntity(StoreDto dto){
        return StoreEntity.builder()
                .storeNo(dto.getStoreNo())
                .storeLocal(dto.getStoreLocal())
                .storeName(dto.getStoreName())
                .storeAddress(dto.getStoreAddress())
                .build();
    }
}


