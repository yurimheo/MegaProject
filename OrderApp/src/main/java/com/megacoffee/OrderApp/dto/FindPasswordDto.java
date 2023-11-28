package com.megacoffee.OrderApp.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindPasswordDto {

    private String userName;
    private String loginId;
    private String userEmail;
}

