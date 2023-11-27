package com.megacoffee.OrderApp.controller;

import com.megacoffee.OrderApp.dto.JoinDto;
import com.megacoffee.OrderApp.dto.LoginDto;
import com.megacoffee.OrderApp.dto.ResultDto;
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
import java.util.List;

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
}
