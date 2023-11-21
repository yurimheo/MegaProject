package com.megacoffee.OrderApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    // 창 이동 - 화면에 보여지는 GetMapping, requestMapping,,,, 맵핑류

    // Localhost:8080 + /login : 로그인 화면으로 이동
    @GetMapping("/")
    public String index(){
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String loginForm(){
        return "loginForm";
    }

    // 회원가입 화면으로 이동
    @GetMapping("/signIn")
    public String signInForm(){return "signlnForm";}

    // 관리자 화면으로 이동
    @GetMapping("/admin")
    public String admin(){return "memberManagement";}

    // 관리자 화면 - 회원 관리 탭으로 이동
    @GetMapping("/admin/member")
    public String admin(){return "memberManagement";}



}
