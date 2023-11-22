package com.megacoffee.OrderApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    // 창 이동 - 화면에 보여지는 GetMapping, requestMapping,,,, 맵핑류

    // <이용자>
    // 1. 로그인 및 회원 가입 시작 -------------------------------------
    // Localhost:8080 + /login : 로그인 화면으로 이동
    @GetMapping("/")
    public String index(){
        return "redirect:/login";
    }
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



    // 아이디 찾기 및 비밀번호 찾기로 이동
    @GetMapping("/findid")
    public String findid(){return  "findId";}
    // 1. 로그인 및 회원 가입 끝 -------------------------------------
}
