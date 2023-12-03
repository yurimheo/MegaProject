package com.megacoffee.OrderApp.controller;
import com.megacoffee.OrderApp.dto.ItemDto;
import com.megacoffee.OrderApp.dto.ResultDto;
import com.megacoffee.OrderApp.entity.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import com.megacoffee.OrderApp.dto.MemberDto;
import com.megacoffee.OrderApp.dto.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminApiController {

  
   // 의존성 주입
    @Autowired
    private MemberRepository memberRepository;

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
    // 1. 회원 관리 관련 끝! ---------------------------------------

    // 2. 상품 관리 ----------------------------------------------
    @Autowired
   private ItemRepository itemRepository;

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

    // 상품 등록
    @PostMapping("/product/addItem")
    public ResultDto addItem(@RequestBody ItemDto itemDto) {

        itemDto.setItemImageUrl(itemDto.getItemImageUrl());

        ItemEntity itemEntity = ItemEntity.toEntity(itemDto);

        ItemEntity newEntity = itemRepository.save(itemEntity);

        ResultDto resultDto = null;

        if( newEntity != null  ) {
            //등록 성공
            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(1)
                    .build();
        }else{
            //등록 실패
            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(0)
                    .build();
        }

        return resultDto;
    }

    // 상품 수정
    @PostMapping("/product/updateItem")
    public ResultDto updateItem(@RequestBody ItemDto itemDto) {
        // 기존 상품을 이름을 기준으로 찾기
        Optional<ItemEntity> optionalItemEntity = itemRepository.findOptionalByItemName(itemDto.getItemName());

        ResultDto resultDto;

        if (optionalItemEntity.isPresent()) {
            // 기존 상품이 존재할 경우 수정 진행
            ItemEntity existingEntity = optionalItemEntity.get();

            // 변경하려는 값으로 엔터티 업데이트
            existingEntity.setItemCate(itemDto.getItemCate());
            existingEntity.setItemPrice(itemDto.getItemPrice());
            existingEntity.setItemImageUrl(itemDto.getItemImageUrl());
            existingEntity.setItemExplanation(itemDto.getItemExplanation());
            existingEntity.setItemNew(itemDto.getItemNew());
            existingEntity.setItemRecommend(itemDto.getItemRecommend());
            existingEntity.setItemUpdateDatetime(itemDto.getItemUpdateDatetime());

            // 엔터티 저장
            ItemEntity updatedEntity = itemRepository.save(existingEntity);

            if (updatedEntity != null) {
                // 수정 성공
                resultDto = ResultDto.builder()
                        .status("ok")
                        .result(1)
                        .build();
            } else {
                // 수정 실패
                resultDto = ResultDto.builder()
                        .status("error")
                        .message("상품 수정 중에 오류가 발생했습니다.")
                        .result(0)
                        .build();
            }
        } else {
            // 기존 상품이 없을 경우
            resultDto = ResultDto.builder()
                    .status("error")
                    .message("상품을 찾을 수 없습니다.")
                    .result(0)
                    .build();
        }

        return resultDto;
    }



    //상품 관리페이지 - 상품검색 기능 구현
    @PostMapping("/product/search")
    public List<ItemDto> searchItems(@RequestBody Map<String, String> params) {
        String searchOption = params.get("searchOption");
        String searchValue = params.get("searchValue");

        List<com.megacoffee.OrderApp.entity.ItemEntity> resultList;
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

    // 3+4. 주문 및 공지 관리 시작 -----------------------------------------
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/notice/deleteSelectedNotice")
    @Transactional
    public ResultDto deleteSelectedNotices(@RequestBody Map<String, List<Integer>> params) {
        List<Integer> noticeNos = params.get("noticeNos");

        try {
            //
            for (long noticeNo : noticeNos ) {
                noticeRepository.deleteByNoticeNo(noticeNo);
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
    //주문 선택삭제
    @PostMapping("/order/deleteSelectedOrder")
    @Transactional
    public ResultDto deleteSelectedOrders(@RequestBody Map<String, List<Integer>> params) {
        List<Integer> orderNos = params.get("orderNos");

        try {
            //
            for (long orderNo : orderNos ) {
                orderRepository.deleteByOrderNo(orderNo);
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
