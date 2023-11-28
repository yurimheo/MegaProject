package com.megacoffee.OrderApp.dto;

import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResultDto {
    String status;
    int result;
    Object data;
    String message;
    LocalDate join_date;
}