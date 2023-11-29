package com.megacoffee.OrderApp.controller;


import com.megacoffee.OrderApp.Entity.ItemEntity;
import com.megacoffee.OrderApp.Entity.ItemRepository;

import com.megacoffee.OrderApp.dto.ItemDto;
import com.megacoffee.OrderApp.dto.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminApiController_LHJ {

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

    @PostMapping("/product/addItems")
    public ResultDto addItems(@RequestBody ItemDto itemDto) {
        // itemDto는 클라이언트에서 보낸 데이터를 ItemDto 타입의 객체로 변환하여 제공합니다.
        // 이후 해당 객체를 활용하여 비즈니스 로직을 수행하거나 원하는 대로 처리할 수 있습니다.

        // 예를 들어, itemDto를 이용하여 데이터베이스에 상품을 추가하고, 결과를 응답할 수 있습니다.

        try {
            // 여기에 상품 추가 로직을 작성해주세요.

            // 상품 추가 후 결과를 응답
            return ResultDto.builder()
                    .status("ok")
                    .result(1)
                    .build();
        } catch (Exception e) {
            // 예외가 발생한 경우
            String errorMessage = "상품 추가 중 오류가 발생했습니다.";
            if (e.getMessage() != null) {
                errorMessage += " 원인: " + e.getMessage();
            }
            return ResultDto.builder()
                    .status("error")
                    .message("상품 추가 중 오류가 발생했습니다.")
                    .build();
        }
    }

    // 1-(1) 회원 관리 - 회원 조회 ---------------------------------
    @PostMapping("/product/search")
    public List<ItemDto> searchItems(@RequestBody Map<String, String> params) {
        String searchOption = params.get("searchOption");
        String searchValue = params.get("searchValue");

        List<ItemEntity> resultList;
        switch (searchOption) {
            case "1":
                int searchIntValue = Integer.parseInt(searchValue);
                resultList = itemRepository.findByItemNew(searchIntValue);
                break;
            case "2":
                resultList = itemRepository.findCoffeeItemsByNameContaining(searchValue);
                break;
            case "3":
                resultList = itemRepository.findTeaItemsByNameContaining(searchValue);
                break;
            case "4":
                resultList = itemRepository.findAdeAndSmoothieItemsByNameContaining(searchValue);
                break;
            case "5":
                resultList = itemRepository.findByItemNameContaining(searchValue);
                break;

            default:
                resultList = itemRepository.findAll();
                break;
        }

        return resultList.stream()
                .map(ItemDto::toDto)
                .collect(Collectors.toList());
    }
}