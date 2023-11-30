package com.megacoffee.OrderApp;

import lombok.Data;

@Data
public class UpdateOrderStatusRequest {
    private Long orderNo;
    private String newStatus;
}

