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

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminViewController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/member")
    public String member(Model model){
        List<MemberEntity> listEntity = memberRepository.findAll();
        List<MemberDto> listDto = listEntity
                .stream()
                .map(MemberDto::toDto)
                .collect(Collectors.toList());

        model.addAttribute("pageName", "회원관리");
        model.addAttribute("count", listDto.size());
        model.addAttribute("list", listDto);

         return "memberManagement";
    }


    // 관리자 화면 - 회원 상세 조회 탭으로 이동
    @GetMapping("/member/detail/{id}")
    public String memberDetail(@PathVariable String id, Model model) {
        // 아이디를 통해 회원 정보 찾기
        List<MemberEntity> members = memberRepository.findByMemberId(id);

        // DB에 회원이 있는가?
        if (!members.isEmpty()) {
            MemberEntity member = members.get(0); // 여러 회원 중 하나를 선택 (첫 번째 회원)
            model.addAttribute("member", member);
            model.addAttribute("pageName", "회원관리");
            return "/memberDetail";  // 회원 상세 정보 조회 페이지로 이동
        } else {
            return "redirect:/admin/member";
        }
    }
}
        // 1. 회원 관리 탭 끝 -------------------------------------------
// 4. 공지 관리 시작 -------------------------------------------
