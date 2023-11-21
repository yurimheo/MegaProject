package com.megacoffee.OrderApp.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    long count();

    // <조회> 회원 아이디 및 비밀번호
    // select * from member where member_id = ? and member_pw = ?
    List<MemberEntity> findByMemberIdAndMemberPw(String id, String pw);

    // <조회> 회원 아이디
    // select * form member where member_id = ?
    List<MemberEntity> findByMemberId(String id);


}
