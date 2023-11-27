package com.megacoffee.OrderApp.controller;

import com.megacoffee.OrderApp.entity.MemberEntity;
import com.megacoffee.OrderApp.entity.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ViewController {

    // 창 이동 - 화면에 보여지는 GetMapping, requestMapping,,,, 맵핑류

    // <이용자>
    // 1. 로그인 및 회원 가입 시작 -------------------------------------
    // Localhost:8080 + /login : 로그인 화면으로 이동

    @Autowired
    private MemberRepository memberRepository;

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

    // 회원가입 화면 3 - 상세 입력
    @GetMapping("/signInForm")
    public String signInForm(){return "/userApp/signInForm";}

    // 회원가입 화면 4 - 완료
    @GetMapping("/signInFinish")
    public String signInFinish(Model model){
        return "/userApp/signInFinish";
    }


    // 아이디 찾기 및 비밀번호 찾기로 이동
    @GetMapping("/findid")
    public String findid(){return  "findId";}
    // 1. 로그인 및 회원 가입 끝 -------------------------------------
    @GetMapping("/main")
    public String main(){return  "/userApp/main";}


