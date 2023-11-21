package com.megacoffee.OrderApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    // 창 이동 - 화면에 보여지는 GetMapping, requestMapping,,,, 맵핑류

    // Localhost:8080 + /login : 로그인 화면으로 이동
    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String main() {
        return "loginForm";
    }

    //관리자 페이지
    @GetMapping("/management")
    public String manager() {
        return "product_management";
    }

    //상품수정
    @GetMapping("/update")
    public String update() {
        return "product_details_update";
    }

    //상품등록
    @GetMapping("/registered")
    public String registered() {
        return "product_details_registered";
    }

    //상품 상세정보
    @GetMapping("/details")
    public String details() {
        return "product_details";
    }
}



