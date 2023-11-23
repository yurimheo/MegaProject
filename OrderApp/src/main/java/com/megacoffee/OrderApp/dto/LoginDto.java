package com.megacoffee.OrderApp.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    private String loginId;
    private String loginPw;
}

