package com.megacoffee.OrderApp.dto;

import com.megacoffee.OrderApp.entity.MemberEntity;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FindIdDto {
    private String userName;
    private String userEmail;

}
