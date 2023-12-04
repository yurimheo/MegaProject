package com.megacoffee.OrderApp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyOrderStateDto {
    private Long orderNo;
    private String newState;
}
