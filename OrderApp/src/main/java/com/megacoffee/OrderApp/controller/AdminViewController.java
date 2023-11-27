package com.megacoffee.OrderApp.controller;

import com.megacoffee.OrderApp.dto.MemberDto;
import com.megacoffee.OrderApp.entity.MemberEntity;
import com.megacoffee.OrderApp.entity.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminViewController {

    // 의존성 주입
    @Autowired
    private MemberRepository memberRepository; // 회원

    // <관리자>
    // 1. 회원 관리 탭 시작 ---------------------------------------------
    // 관리자 화면 - 회원 관리 탭으로 이동
    @GetMapping("/member")
    public String member(Model model){
        List<MemberEntity> listEntity = memberRepository.findAll();

        List<MemberDto> listDto = listEntity
                .stream()
                .map(MemberDto::toDto)
                .collect(Collectors.toList());

        model.addAttribute("count", listDto.size());
        model.addAttribute("list", listDto);

        return "memberManagement";
    }

    // 관리자 화면 - 회원 상세 조회 탭으로 이동
    @GetMapping("/member/check")
    public String member2(){return "memberDetail";}
    // 1. 회원 관리 탭 끝 -----------------------------------------------



}
