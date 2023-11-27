package com.megacoffee.OrderApp.controller;

import com.megacoffee.OrderApp.dto.*;
import com.megacoffee.OrderApp.entity.MemberEntity;
import com.megacoffee.OrderApp.entity.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

// @RestController : 해당 클래스가 'RESTfulAPI'를 제공하는 컨트롤러임을 나타내는 어노테이션
@RestController
public class ApiController {
    // 기능 구현

    // <이용자>
    // 1. 로그인 및 회원가입 시작 -------------------------------------
    @Autowired
    private MemberRepository memberRepository;

    @PostMapping("/loginAction")
    public ResultDto loginAction(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        System.out.println("loginId:"+loginDto.getLoginId());
        System.out.println("loginPw:"+loginDto.getLoginPw());

        //로그인 액션 : 아이디, 암호가 DB에 있으면 로그인 성공, 아니면 실패
        List<MemberEntity> list = memberRepository.findByMemberIdAndMemberPw(
                loginDto.getLoginId(),
                loginDto.getLoginPw()
        );

        ResultDto resultDto = null;

        if( list.size() > 0 ) {
            //로그인 성공
            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(1)
                    .build();
            request.getSession().setAttribute("loginId", loginDto.getLoginId());
            //request.getSession().invalidate(); //로그아웃처리
        }else{
            //로그인 실패
            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(0)
                    .build();
        }

        return resultDto;
    }

    @PostMapping("/checkIdAction")
    public  ResultDto checkIdAction(@RequestBody LoginDto loginDto) {
        try {
            System.out.println("loginId:" + loginDto.getLoginId());

            //로그인 액션 : 아이디 있으면 되돌아가기, 없으면 회원가입 이어서 진행
            List<MemberEntity> list = memberRepository.findByMemberId(
                    loginDto.getLoginId()
            );

            ResultDto resultDto = null;
            if( list.size() > 0 ) {
                // 아이디가 존재하면 회원가입 불가능
                resultDto = ResultDto.builder()
                        .status("ok")
                        .result(0)
                        .build();
            }else{
                // 아이디가 존재하지 않으면 회원가입 가능
                resultDto = ResultDto.builder()
                        .status("ok")
                        .result(1)
                        .build();
            }

            return resultDto;
        } catch (Exception e) {
            return ResultDto.builder()
                    .status("error")
                    .build();
        }
    }

    @PostMapping("/joinAction")
    public ResultDto joinAction(@RequestBody JoinDto joinDto, Model model) {
        System.out.println("loginId:"+joinDto.getLoginId());
        System.out.println("loginPw:"+joinDto.getLoginPw());
        System.out.println("userName:"+joinDto.getUserName());
        System.out.println("userBirth:"+joinDto.getUserBirth());
        System.out.println("userEmail:"+joinDto.getUserEmail());
        System.out.println("userPhone:"+joinDto.getUserPhone());

        MemberEntity memberJoinEntity = MemberEntity.toJoinEntity( joinDto );

        // 새 멤버 엔티티를 리포지터리에 저장함
        MemberEntity newEntity = memberRepository.save(memberJoinEntity);

        ResultDto resultDto = null;

        if( newEntity != null  ) {
            //회원가입 성공
            model.addAttribute("memberName", joinDto.getUserName());
            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(1)
                    .build();
        }else{
            //회원가입 실패
            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(0)
                    .build();
        }

        return resultDto;
    }

    // 아이디 및 비번 찾기 - 1. 아이디 찾기 -------------------------
    @PostMapping("/findIdAction")
public ResultDto findIdAction(@RequestBody FindIdDto findIdDto) {
    try {
        // 이름과 이메일을 이용해 회원을 찾음
        List<MemberEntity> members = memberRepository.findByMemberNameAndMemberEmail(
                findIdDto.getUserName(),
                findIdDto.getUserEmail()
        );

        ResultDto resultDto = null;
        if (!members.isEmpty()) {
            // 찾은 회원이 있으면 아이디를 응답에 포함시켜 전송
            MemberEntity foundMember = members.get(0);
            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(1)
                    .data(foundMember.getMemberId())
                    .join_date(foundMember.getMemberJoinDate())
                    .build();
        } else {
            // 찾은 회원이 없으면 실패 응답
            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(0)
                    .build();
        }

        return resultDto;
    } catch (Exception e) {
        // 에러 발생 시 에러 응답
        return ResultDto.builder()
                .status("error")
                .build();
        }
    }

    // 아이디 및 비번 찾기 - 2. 비밀번호 찾기 -----------------------
    @PostMapping("/findPwAction")
    public ResultDto findPwAction(@RequestBody FindPasswordDto findPasswordDto, HttpServletRequest request) {
        try {
            String userName = findPasswordDto.getUserName();
            String userEmail = findPasswordDto.getUserEmail();
            String loginId = findPasswordDto.getLoginId();

            // 유효성 검사
            if (userName == null || userEmail == null || loginId == null) {
                return ResultDto.builder()
                        .status("error")
                        .message("유효하지 않은 입력입니다.")
                        .build();
            }

            List<MemberEntity> members = memberRepository.findByMemberNameAndMemberEmailAndMemberId(
                    userName,
                    userEmail,
                    loginId
            );

            ResultDto resultDto;
            if (!members.isEmpty()) {
                MemberEntity foundMember = members.get(0);
                request.getSession().setAttribute("loginId", foundMember.getMemberId());
                resultDto = ResultDto.builder()
                        .status("ok")
                        .result(1)
                        .data(foundMember.getMemberId())
                        .join_date(foundMember.getMemberJoinDate())
                        .build();
            } else {
                resultDto = ResultDto.builder()
                        .status("ok")
                        .result(0)
                        .message("회원을 찾을 수 없습니다.")
                        .build();
            }

            return resultDto;
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return ResultDto.builder()
                    .status("error")
                    .message("에러가 발생했습니다!")
                    .build();
        }
    }

    // 아이디 및 비밀번호 찾기 2-2. 비밀번호 수정 ---------------------
@PostMapping("/modifyPasswordAction")
public ResultDto modifyPasswordAction(
        @RequestBody ModifyPasswordDto modifyPasswordDto,
        HttpServletRequest request
) {
    try {
        // 아이디 가져오기
        String loginId = (String) request.getSession().getAttribute("loginId");

        // 로그인 아이디로 사용자 정보 조회
        List<MemberEntity> members = memberRepository.findByMemberId(loginId);

        ResultDto resultDto;

        if (!members.isEmpty()) {
            MemberEntity member = members.get(0);

            // 새로운 비밀번호 설정
            member.setMemberPw(modifyPasswordDto.getNewPassword());

            // 비밀번호 업데이트
            memberRepository.save(member);

            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(1)
                    .build();
        } else {
            // 로그인 아이디로 사용자를 찾을 수 없음
            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(0)
                    .build();
        }

        return resultDto;
    } catch (Exception e) {
        // 에러 발생 시 에러 응답
        return ResultDto.builder()
                .status("error")
                .build();
    }
}

}
