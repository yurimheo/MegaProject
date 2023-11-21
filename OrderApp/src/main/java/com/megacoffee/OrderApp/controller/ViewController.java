package com.megacoffee.OrderApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    // 창 이동 - 화면에 보여지는 GetMapping, requestMapping,,,, 맵핑류

    // Localhost:8080 + /login : 로그인 화면으로 이동
    @GetMapping("/")
    public String index(){
        return "managementAnnouncement";
    }
    @GetMapping("/login")
    public String main(){
        return "loginForm";
    }

}
