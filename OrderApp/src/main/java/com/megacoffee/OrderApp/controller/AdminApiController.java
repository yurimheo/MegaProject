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

    @Autowired
    ItemRepository itemRepository;

    @PostMapping("/product/deleteItems")
    @Transactional
    public ResultDto deleteItems(@RequestBody Map<String, List<String>> params) {
        List<String> itemNames = params.get("itemNames");

        try {
            // 회원 아이디에 해당하는 회원을 삭제
            for (String itemName : itemNames) {
                itemRepository.deleteByItemName(itemName);
            }

            // 삭제 후 결과를 응답
            return ResultDto.builder()
                    .status("ok")
                    .result(1)
                    .build();
        } catch (Exception e) {
            // 예외가 발생한 경우
            String errorMessage = "삭제 중 오류가 발생했습니다.";
            if (e.getMessage() != null) {
                errorMessage += " 원인: " + e.getMessage();
            }
            return ResultDto.builder()
                    .status("error")
                    .message("삭제 중 오류가 발생했습니다.")
                    .build();
        }
    }
}
