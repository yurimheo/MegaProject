package com.megacoffee.OrderApp;

import lombok.Data;

@Data
public class ItemRequest {
    private String itemName;
    private int amount;
}
