package com.megacoffee.OrderApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/admin")
public class AdminViewController {

    // <관리자>
    @GetMapping("/")
    public String admin(){return "memberManagement";}

    // 1. 회원 관리 탭 시작 ---------------------------------------------
    // 관리자 화면 - 회원 관리 탭으로 이동
    @GetMapping("/member")
    public String member(){return "memberManagement";}

    // 관리자 화면 - 회원 상세 조회 탭으로 이동
    @GetMapping("/member/check")
    public String member2(){return "memberDetail";}
    // 1. 회원 관리 탭 끝 -----------------------------------------------



}
