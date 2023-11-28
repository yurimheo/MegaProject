package com.megacoffee.OrderApp.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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