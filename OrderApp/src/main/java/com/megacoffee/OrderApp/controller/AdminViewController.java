package com.megacoffee.OrderApp.controller;

import com.megacoffee.OrderApp.dto.NoticeDto;
import com.megacoffee.OrderApp.dto.OrderDto;
import com.megacoffee.OrderApp.entity.*;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;


@Controller
@RequestMapping("/admin")
public class AdminViewController {



    @Autowired
    private NoticeRepository noticeRepository;
    private MemberRepository memberRepository;
    private OrderRepository orderRepository;



    // <관리자>
    // 1. 회원 관리 탭 시작 ---------------------------------------------
    // 관리자 화면 - 회원 관리 탭으로 이동
    @GetMapping("/member")
    public String member(){return "memberManagement";}

    // 관리자 화면 - 회원 상세 조회 탭으로 이동
    @GetMapping("/member/check")
    public String member2(){return "memberDetail";}
    // 1. 회원 관리 탭 끝 -----------------------------------------------

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

//    @GetMapping("/order")
//    public String managementOrders(Model model){
//        List<OrderEntity> listEntity = orderRepository.findAll();
//
//        List<OrderDto> listDto = listEntity
//                .stream()
//                .map(OrderDto::toOrderDto)
//                .collect(Collectors.toList());
//
//        model.addAttribute("pageName","주문관리");
//        model.addAttribute("count",listDto.size());
//        model.addAttribute("list",listDto);
//
//
//        return "managementOrder";
//    }


}
