package com.megacoffee.OrderApp.entity;

import com.megacoffee.OrderApp.dto.JoinDto;
import com.megacoffee.OrderApp.dto.MemberDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="member")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberEntity {

    @Id  //기본키로 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long memberNo; // 고유키

    @Column(name = "member_id")
    private String memberId; // 아이디

    @Column(name = "member_pw")
    private String memberPw; // 비밀번호

    @Column(name = "member_name")
    private String memberName; // 이름

    @Column(name = "member_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate memberBirth; // 생일

    @Column(name = "member_email")
    private String memberEmail; // 이메일

    @Column(name = "member_phone")
    private String memberPhone; // 연락처

    @Column(name = "member_role")
    private String memberRole; //권한 "admin" "user"

    @Column(name = "member_join_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate memberJoinDate = LocalDate.now();

    @Column(name = "member_stamp")
    private Integer memberStamp; // 멤버 스탬프

    public static MemberEntity toJoinEntity(JoinDto dto) {
        return MemberEntity.builder()
                .memberNo(0L)
                .memberId(dto.getLoginId())
                .memberPw(dto.getLoginPw())
                .memberName(dto.getUserName())
                .memberBirth(dto.getUserBirth())
                .memberEmail(dto.getUserEmail())
                .memberPhone(dto.getUserPhone())
                .memberRole("ROLE_USER")
                .memberJoinDate(LocalDate.now())
                .memberStamp(0)
                .build();
    }
    public static MemberEntity toMemberEntity(MemberDto dto){
        return MemberEntity.builder()
                .memberNo(dto.getMemberNo())
                .memberId(dto.getMemberId())
                .memberPw(dto.getMemberPw())
                .memberName(dto.getMemberName())
                .memberRole(dto.getMemberRole())
                .memberStamp(dto.getMemberStamp())
                .memberJoinDate(dto.getMemberJoinDate())
                .build();
    }

    public void setMemberPassword(String memberPw) {
        this.memberPw = memberPw;
    }
}
