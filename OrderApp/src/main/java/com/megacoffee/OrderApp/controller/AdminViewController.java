package com.megacoffee.OrderApp.controller;

import com.megacoffee.OrderApp.dto.ItemDto;
import com.megacoffee.OrderApp.dto.MemberDto;
import com.megacoffee.OrderApp.dto.NoticeDto;
import com.megacoffee.OrderApp.dto.OrderDto;
import com.megacoffee.OrderApp.entity.*;
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

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private NoticeRepository noticeRepository;

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
        // 1. 회원 관리 탭 끝 -------------------------------------------

    // 2. 상품 관리 -------------------------------------------------

    @Autowired
    private ItemRepository itemRepository;

    // 창 이동 - 화면에 보여지는 GetMapping, requestMapping,,,, 맵핑류

    //관리자 페이지
    @GetMapping("/product_management")
    public String productManagement(Model model) {
        List<ItemEntity> listEntity = itemRepository.findAll();

        // ItemEntity 리스트를 ItemDto 리스트로 변환
        List<ItemDto> listDto = listEntity.stream()
                .map(ItemDto::fromEntity) // ItemEntity를 ItemDto로             변환
                .collect(Collectors.toList());

        model.addAttribute("pageName", "상품관리");
        model.addAttribute("count", listDto.size());
        model.addAttribute("items", listDto); // items라는 이름으로 listDto를 모델에 추가

        return "product_management";
    }

    //상품신규등록
    @GetMapping("/product_details_registered")
    public String productDetailsRegistered(Model model) {

        model.addAttribute("pageName", "상품관리");
        return "product_details_registered";
    }

// 상품관리화면 - 상품 상세 조회 탭으로 이동
@GetMapping("/item/detail/{name}")
public String itemDetail(@PathVariable String name , Model model) {
    // 아이디를 통해 회원 정보 찾기
    List<ItemEntity> items = itemRepository.findByItemName(name);

    // DB에 상품이 있는가?
    if (!items.isEmpty()) {
        ItemEntity item = items.get(0); // 여러 회원 중 하나를 선택 (첫 번째 회원)
        model.addAttribute("item", item);
        model.addAttribute("pageName", "상품관리");
        return "/product_details";  // 회원 상세 정보 조회 페이지로 이동
    } else {
        return "redirect:/admin/product_management";
    }
}

    // 상품관리화면 - 상품 수정 탭으로 이동
    @GetMapping("/item/update/{name}")
    public String itemUpdate(@PathVariable String name , Model model) {
        // 상품명을 통해 상품 정보 찾기
        List<ItemEntity> items = itemRepository.findByItemName(name);

        // DB에 상품이 있는가?
        if (!items.isEmpty()) {
            ItemEntity item = items.get(0); // 여러 회원 중 하나를 선택 (첫 번째 회원)
            model.addAttribute("item", item);
            model.addAttribute("pageName", "상품관리");
            return "product_details_update";
        } else {
            return "redirect:/admin/product_management";
        }
    }


// 3+4. 주문 및 공지 관리 시작 -------------------------------------------
@GetMapping("/notice")
public String notices(Model model){
    List<NoticeEntity> listEntity = noticeRepository.findAll();

    List<NoticeDto> listDto = listEntity
            .stream()
            .map(NoticeDto::toDto)
            .collect(Collectors.toList());

    model.addAttribute("pageName", "공지관리");
    model.addAttribute("count", listDto.size());
    model.addAttribute("list", listDto);

    return "managementAnnouncement";
}

// 공지 신규 등록
    @GetMapping("/notice/add")
    public String noticeAdd(Model model) {
        model.addAttribute("pageName", "공지관리");
    return "managementNotice";
    }


    // 공지 상세 조회
    @GetMapping("/notice/detail/{no}")
    public String noticeDetail(@PathVariable long no, Model model) {

        List<NoticeEntity> notices = noticeRepository.findByNoticeNo(no);


        if (!notices.isEmpty()) {
            NoticeEntity notice = notices.get(0);
            model.addAttribute("notice", notice);
            model.addAttribute("pageName", "공지관리");
            return "/managementNoticeDetailed";
        } else {
            return "redirect:/admin/notice";
        }
    }



    @GetMapping("/order")
    public String orders(Model model){
        List<OrderEntity> listEntity = orderRepository.findAll();

        List<OrderDto> listDto = listEntity
                .stream()
                .map(OrderDto::toOrderDto)
                .collect(Collectors.toList());

        model.addAttribute("pageName","주문관리");
        model.addAttribute("count",listDto.size());
        model.addAttribute("list",listDto);


        return "managementOrder";
    }
    @GetMapping("/order/detail/{no}")
    public String orderDetail(@PathVariable long no, Model model) {

        List<OrderEntity> orders = orderRepository.findByOrderNo(no);


        if (!orders.isEmpty()) {
            OrderEntity order = orders.get(0);
            model.addAttribute("order", order);
            model.addAttribute("pageName", "주문관리");
            return "/managementOrderDetailed";
        } else {
            return "redirect:/admin/order";
        }
    }

    // 공지 관리 - 공지 수정 탭으로 이동
    @GetMapping("/notice/update/{no}")
    public String noticeUpdate(@PathVariable long no , Model model) {
        // 공지 번호를 통해 상품 정보 찾기
        List<NoticeEntity> notices = noticeRepository.findByNoticeNo(no);

        // DB에 그 공지가 있는가?
        if (!notices.isEmpty()) {
            NoticeEntity notice = notices.get(0); // 여러 공지 중 하나를 선택 (첫 번째 공지)
            model.addAttribute("notice", notice);
            model.addAttribute("pageName", "공지관리");
            return "managementNoticeUpdate";
        } else {
            return "redirect:/admin/notice";
        }
    }
}
