package com.megacoffee.OrderApp.controller;

import com.megacoffee.OrderApp.Entity.ItemEntity;
import com.megacoffee.OrderApp.Entity.ItemRepository;
import com.megacoffee.OrderApp.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminViewController_LHJ {

    @Autowired
    private ItemRepository itemRepository;

    // 창 이동 - 화면에 보여지는 GetMapping, requestMapping,,,, 맵핑류

    //관리자 페이지
    @GetMapping("/product_management")
    public String productManagement(Model model) {
        List<ItemEntity> listEntity = itemRepository.findAll();

        // ItemEntity 리스트를 ItemDto 리스트로 변환
        List<ItemDto> listDto = listEntity.stream()
                .map(ItemDto::fromEntity) // ItemEntity를 ItemDto로 변환
                .collect(Collectors.toList());

        model.addAttribute("count", listDto.size());
        model.addAttribute("items", listDto); // items라는 이름으로 listDto를 모델에 추가

        return "product_management";
    }

    //상품수정
    @GetMapping("/product_details_update")
    public String productDetailsUpdate() {
        return "product_details_update";
    }

    //상품신규등록
    @GetMapping("/product_details_registered")
    public String productDetailsRegistered() {
        return "product_details_registered";
    }

    //상품 상세정보
    @GetMapping("/product_details")
    public String productDetails(Model model) {
        return "product_details";
    }
}



