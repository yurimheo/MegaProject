package com.megacoffee.OrderApp.controller;


import com.megacoffee.OrderApp.Entity.ItemRepository;

import com.megacoffee.OrderApp.dto.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import com.megacoffee.OrderApp.dto.MemberDto;
import com.megacoffee.OrderApp.dto.ResultDto;
import com.megacoffee.OrderApp.entity.MemberEntity;
import com.megacoffee.OrderApp.entity.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminApiController {

  
   // 의존성 주입
    @Autowired
    private MemberRepository memberRepository; // 회원

    @Autowired
    private ItemRepository itemRepository;

 // 1-(1) 회원 관리 - 회원 조회 ---------------------------------
    @PostMapping("/member/search")
    public List<MemberDto> searchMembers(@RequestBody Map<String, String> params) {
        String searchOption = params.get("searchOption");
        String searchValue = params.get("searchValue");
        List<MemberEntity> resultList;
        switch (searchOption) {
            case "1":
                resultList = memberRepository.findByMemberNameContaining(searchValue);
                break;
            case "2":
                resultList = memberRepository.findByMemberIdContaining(searchValue);
                break;
            case "3":
                resultList = memberRepository.findByMemberNameContainingOrMemberIdContaining(searchValue, searchValue);
                break;
            default:
                resultList = memberRepository.findAll();
                break;
        }
        return resultList.stream()
                .map(MemberDto::toDto)
                .collect(Collectors.toList());
    }
    // 1-(2). 회원 관리 - 회원 삭제 --------------------------------
    @PostMapping("/member/deleteSelectedMembers")
     @Transactional
    public ResultDto deleteSelectedMembers(@RequestBody Map<String, List<String>> params) {
        List<String> memberIds = params.get("memberIds");
           try {
            // 회원 아이디에 해당하는 회원을 삭제
            for (String memberId : memberIds) {
                memberRepository.deleteByMemberId(memberId);
                  }
            // 삭제 후 결과를 응답
                    .build();
        }
    }
    // 1. 회원 관리 관련 끝! ---------------------------------------
    // 4. 공지 관리 시작 -----------------------------------------
}
