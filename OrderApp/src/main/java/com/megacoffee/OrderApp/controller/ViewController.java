package com.megacoffee.OrderApp.controller;

import com.megacoffee.OrderApp.dto.FindIdDto;
import com.megacoffee.OrderApp.entity.MemberEntity;
import com.megacoffee.OrderApp.entity.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ViewController {

    // <이용자>
    // 1. 로그인 및 회원 가입 시작 -------------------------------------
    // Localhost:8080 + /login : 로그인 화면으로 이동

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/")
    public String index(){
        return "redirect:/loading";
    } //추후 로딩화면으로 수정

    @GetMapping("/loading")
    public String loading(){return "/userApp/loading";}

    @GetMapping("/login")
    public String loginForm(){
        return "/userApp/loginForm";
    }

    // 회원가입 화면 1
    @GetMapping("/signInCheckID")
    public String signInCheckID(){
        return "/userApp/signInCheckID";
    }

    // 회원가입 화면 2 - 이용약관
    @GetMapping("/termsOfUse")
    public String tou(){
        return "/userApp/termsOfUse";
    }

    // 회원가입 화면 2-(1) 멤버십 이용약관
    @GetMapping("/membershipTOU")
    public String membershipTOU(){
        return "/userApp/membershipTOU";
    }

    // 회원가입 화면 2-(2) 유저 이용약관
    @GetMapping("/userTOU")
    public String userTOU(){
        return "/userApp/userTOU";
    }

    // 회원가입 화면 3 - 상세 입력
    @GetMapping("/signInForm")
    public String signInForm(){return "/userApp/signInForm";}

    // 회원가입 화면 4 - 완료
    @GetMapping("/signInFinish")
    public String signInFinish(Model model){
        return "/userApp/signInFinish";
    }


    // 아이디 찾기 및 비밀번호 찾기로 이동
    // 1. 아이디 찾기
    @GetMapping("/findId")
    public String findId(){return  "/userApp/findId";}

    // 1-2. 아이디 찾기 완료
    @GetMapping("/resultId")
    public String resultId(Model model,
                           @RequestParam(name = "member_id", required = false) String memberId,
                           @RequestParam(name = "join_date", required = false) LocalDate joinDate) {
        // 아이디 가져오기
        model.addAttribute("member_id", memberId);

        model.addAttribute("join_date", joinDate);

        return "/userApp/findId2";
    }

    // 2. 비밀번호 찾기
    @GetMapping("/findPw")
    public String findPw(){return  "/userApp/findPw";}

    // 2-2. 비밀번호 찾기 완료
    @GetMapping("/resultPw")
    public String resultPw(Model model,
                           @RequestParam(name = "userName", required = false) String userName){

        System.out.println("전달 받은 이름 : " + userName);

        // 이름 가져오기
        model.addAttribute("member_name", userName);
        return  "/userApp/findPw2";
    }

    // 1. 로그인 및 회원 가입 끝 -------------------------------------
}

