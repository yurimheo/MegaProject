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

    boolean existsByMemberId(String id);

    // <조회> 회원 이름 및 이메일
    List<MemberEntity> findByMemberNameAndMemberEmail(String userName, String userEmail);

    // <조회> 회원 이름 및 이메일, 아이디
    List<MemberEntity> findByMemberNameAndMemberEmailAndMemberId(String userName, String userEmail, String loginId);

    // <삭제> 삭제할 아이디를 찾아서 삭제하기
    void deleteByMemberId(String memberId);

    // <조회> 회원 이름 (부분 문자열 포함 확인)
    List<MemberEntity> findByMemberNameContaining(String searchValue);
    // <조회> 회원 아이디 (부분 문자열 포함 확인)
    List<MemberEntity> findByMemberIdContaining(String searchValue);
    // <조회> 회원 이름이나 아이디 (부분 문자열 포함 확인)
    List<MemberEntity> findByMemberNameContainingOrMemberIdContaining(String searchValue, String searchValue1);
}
