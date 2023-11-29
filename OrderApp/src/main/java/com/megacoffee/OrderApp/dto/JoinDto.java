package com.megacoffee.OrderApp.dto;
import com.megacoffee.OrderApp.entity.MemberEntity;
import lombok.*;
import java.time.LocalDate;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JoinDto {
    private String loginId;
    private String loginPw;
    private String userName;
    private LocalDate userBirth;
    private String userEmail;
    private String userPhone;
    public static JoinDto toDto(MemberEntity entity){
        return JoinDto.builder()
                .loginId(entity.getMemberId())
                .loginPw(entity.getMemberPw())
                .userName(entity.getMemberName())
                .userBirth(entity.getMemberBirth())
                .userEmail(entity.getMemberEmail())
                .userPhone(entity.getMemberPhone())
                .build();
    }
}