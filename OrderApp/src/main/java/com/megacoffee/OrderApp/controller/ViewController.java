package com.megacoffee.OrderApp.controller;

import com.megacoffee.OrderApp.dto.*;
import com.megacoffee.OrderApp.entity.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class ViewController {

    // <이용자>
    // 1. 로그인 및 회원 가입 시작 -------------------------------------
    // Localhost:8080 + /login : 로그인 화면으로 이동

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/")
    public String index() {
        return "redirect:/loading";
    }

    @GetMapping("/loading")
    public String loading(){return "/userApp/loading";}

    @GetMapping("/login")
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

    // 회원가입 화면 2-(1) 멤버십 이용약관
    @GetMapping("/membershipTOU")
    public String membershipTOU(){
        return "/userApp/membershipTOU";
    }

    // 회원가입 화면 2-(2) 유저 이용약관
    @GetMapping("/userTOU")
    public String userTOU(){
        return "/userApp/userTOU";
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
    // 1. 아이디 찾기
    @GetMapping("/findId")
    public String findId(){return  "/userApp/findId";}

    // 1-2. 아이디 찾기 완료
    @GetMapping("/resultId")
    public String resultId(Model model,
                           @RequestParam(name = "member_id", required = false) String memberId,
                           @RequestParam(name = "join_date", required = false) LocalDate joinDate) {
        // 아이디 가져오기
        model.addAttribute("member_id", memberId);

        model.addAttribute("join_date", joinDate);

        return "/userApp/findId2";
    }

    // 2. 비밀번호 찾기
    @GetMapping("/findPw")
    public String findPw(){return  "/userApp/findPw";}

    // 2-2. 비밀번호 찾기 완료
    @GetMapping("/resultPw")
    public String resultPw(Model model,
                           @RequestParam(name = "userName", required = false) String userName){

        System.out.println("전달 받은 이름 : " + userName);

        // 이름 가져오기
        model.addAttribute("member_name", userName);
        return  "/userApp/findPw2";
    }


    // 1. 로그인 및 회원 가입 끝 -------------------------------------

    // 2. 메인 시작 ---------------------------------------------
    @Autowired
    private NoticeRepository noticeRepository;

    @GetMapping("/main")
    public String main(Model model, HttpServletRequest request){
        String loginId = (String)request.getSession().getAttribute("loginId");
        if( loginId != null ){
            List<MemberEntity> list = memberRepository.findByMemberId(loginId);
            if( list.size() > 0 ){
                MemberEntity memberEntity = list.get(0);
                model.addAttribute("loginName", memberEntity.getMemberName());
                model.addAttribute("stamp", memberEntity.getMemberStamp());
            }
        }
        // 메인 공지 불러오기
        List<NoticeEntity> listEntity = noticeRepository.findAll();

        List<NoticeDto> listDto = listEntity
                .stream()
                .map(NoticeDto::toDto)
                .collect(Collectors.toList());

        model.addAttribute("count", listDto.size());
        model.addAttribute("list", listDto);

        //추천 메뉴 불러오기
        List<ItemEntity> itemEntity = itemRepository.findAll();

        List<ItemDto> itemDto = itemEntity
                .stream()
                .map(ItemDto::toDto)
                .collect(Collectors.toList());

        model.addAttribute("count2", itemDto.size());
        model.addAttribute("list2", itemDto);
        return  "/userApp/main";}

    // 스탬프
    @GetMapping("/stamp")
    public String stamp(Model model, HttpServletRequest request){
        String loginId = (String)request.getSession().getAttribute("loginId");
        if( loginId != null ){
            List<MemberEntity> list = memberRepository.findByMemberId(loginId);
            if( list.size() > 0 ){
                MemberEntity memberEntity = list.get(0);
                model.addAttribute("loginName", memberEntity.getMemberName());
                model.addAttribute("stamp", memberEntity.getMemberStamp());
            }
        }
        return "/userApp/Stamp";
    }

    // 알림
    @GetMapping("/notification")
    public String notification(Model model, HttpServletRequest request){
        String loginId = (String)request.getSession().getAttribute("loginId");
        if( loginId != null ){
            List<MemberEntity> list = memberRepository.findByMemberId(loginId);
            if( list.size() > 0 ){
                MemberEntity memberEntity = list.get(0);
                model.addAttribute("loginId", loginId);
                model.addAttribute("loginName", memberEntity.getMemberName());
                model.addAttribute("stamp", memberEntity.getMemberStamp());
            }
        }

        // 주문내역 DB 불러오기
        List<OrderEntity> orderEntity = orderRepository.findAll();

        List<OrderDto> orderDto = orderEntity
                .stream()
                .map(OrderDto::toDto)
                .collect(Collectors.toList());

        model.addAttribute("count", orderDto.size());
        model.addAttribute("list", orderDto);
        return "/userApp/notification";
    }

    @Autowired
    private OrderRepository orderRepository;

    // 스탬프 적립내역
    @GetMapping("/stampEarn")
    public String stampEarn(Model model, HttpServletRequest request){
        String loginId = (String)request.getSession().getAttribute("loginId");
        if( loginId != null ){
            List<MemberEntity> list = memberRepository.findByMemberId(loginId);
            if( list.size() > 0 ){
                MemberEntity memberEntity = list.get(0);
                model.addAttribute("loginId", loginId);
                model.addAttribute("loginName", memberEntity.getMemberName());
                model.addAttribute("stamp", memberEntity.getMemberStamp());
            }
        }

        // 주문내역 DB 불러오기
        List<OrderEntity> orderEntity = orderRepository.findAll();

        List<OrderDto> orderDto = orderEntity
                .stream()
                .map(OrderDto::toDto)
                .collect(Collectors.toList());

        model.addAttribute("count", orderDto.size());
        model.addAttribute("list", orderDto);
        return "/userApp/stamp_earnHistory";
    }

    // 2. 메인 끝 -----------------------------------------------

    // 3. 주문/결제하기 시작 -------------------------------------
    // 3. 장바구니
    @Autowired
    private CartRepository cartRepository;
    @GetMapping("/cart")
    public String carts(Model model){
        List<CartEntity> listEntity = cartRepository.findAll();

        List<CartDto> listDto = listEntity
                .stream()
                .map(CartDto::toCartDto)
                .collect(Collectors.toList());

        model.addAttribute("count", listDto.size());
        model.addAttribute("list", listDto);

        return "/userApp/orderPage";
    }
    // 4. 결제
    // 4. 결제
    @GetMapping("/order")
    public String order(Model model,HttpServletRequest request){
        String loginId = (String)request.getSession().getAttribute("loginId");
        List<CartEntity> listEntity = cartRepository.findAll();

        List<CartDto> listDto = listEntity
                .stream()
                .map(CartDto::toCartDto)
                .collect(Collectors.toList());

        if( loginId != null ){
            List<MemberEntity> list = memberRepository.findByMemberId(loginId);
            if( list.size() > 0 ){
                MemberEntity memberEntity = list.get(0);
                model.addAttribute("loginId", loginId);
            }
        }

        model.addAttribute("count", listDto.size());

        model.addAttribute("list", listDto);
        return "/userApp/orderPage2";
    }
    // 3. 주문/결제하기 끝 ---------------------------------------

    // 4. 더보기 시작 -------------------------------------------
    // 4-(1) 더보기
     @GetMapping("/more")
    public String more(Model model, HttpServletRequest request){
         String loginId = (String)request.getSession().getAttribute("loginId");
        if( loginId != null ){
            List<MemberEntity> list = memberRepository.findByMemberId(loginId);
            if( list.size() > 0 ){
                MemberEntity memberEntity = list.get(0);
                model.addAttribute("loginId", loginId);
                model.addAttribute("loginName", memberEntity.getMemberName());
            }
        }
        return "/userApp/more";
    }

    @GetMapping("/help")
    public String help(){
        return "/userApp/help";
    }


    // 이벤트 (메인과 이어짐)
    @GetMapping("/newnews")
    public String news(Model model){
        List<NoticeEntity> listEntity = noticeRepository.findAll();

        List<NoticeDto> listDto = listEntity
                .stream()
                .map(NoticeDto::toDto)
                .collect(Collectors.toList());

        model.addAttribute("count", listDto.size());
        model.addAttribute("list", listDto);
        return "/userApp/newnews";
    }

    // 이벤트 (더보기와 이어짐)
    @GetMapping("/newEvent")
    public String newEvent(Model model){
        List<NoticeEntity> listEntity = noticeRepository.findAll();

        List<NoticeDto> listDto = listEntity
                .stream()
                .map(NoticeDto::toDto)
                .collect(Collectors.toList());

        model.addAttribute("count", listDto.size());
        model.addAttribute("list", listDto);
        return "/userApp/newEvent";
    }

    // 공지사항 (메인과 이어짐)
    @GetMapping("/newnews2")
    public String news2(Model model){
        List<NoticeEntity> listEntity = noticeRepository.findAll();

        List<NoticeDto> listDto = listEntity
                .stream()
                .map(NoticeDto::toDto)
                .collect(Collectors.toList());

        model.addAttribute("count", listDto.size());
        model.addAttribute("list", listDto);
        return "/userApp/newnews2";
    }

    // 공지사항 (더보기와 이어짐)
    @GetMapping("/newNotice")
    public String newNotice(Model model){
        List<NoticeEntity> listEntity = noticeRepository.findAll();

        List<NoticeDto> listDto = listEntity
                .stream()
                .map(NoticeDto::toDto)
                .collect(Collectors.toList());

        model.addAttribute("count", listDto.size());
        model.addAttribute("list", listDto);
        return "/userApp/newNotice";
    }

    @GetMapping("/notice/details/{no}")
    public String noticeDetail(@PathVariable long no, Model model) {

        List<NoticeEntity> notices = noticeRepository.findByNoticeNo(no);


        if (!notices.isEmpty()) {
            NoticeEntity notice = notices.get(0);
            model.addAttribute("notice", notice);
            model.addAttribute("pageName", "공지");
            return "/userApp/newsFeed";
        } else {
            return "redirect:/userApp/newsFeed";
        }
    }

    // 4-(2) 계정 관리
    @GetMapping("/memberSetting")
    public String memberSetting(Model model, HttpServletRequest request){
        String loginId = (String)request.getSession().getAttribute("loginId");
        if( loginId != null ){
            List<MemberEntity> list = memberRepository.findByMemberId(loginId);
            if( list.size() > 0 ){
                MemberEntity memberEntity = list.get(0);
                model.addAttribute("loginName", memberEntity.getMemberName());
                model.addAttribute("memberBirth", memberEntity.getMemberBirth());
                model.addAttribute("memberPhone", memberEntity.getMemberPhone());
            }
        }

        return "/userApp/memberSetting";
    }

    // 4-(3) 회원 탈퇴
    @GetMapping("/deleteMember")
    public String deleteMember(Model model, HttpServletRequest request){
        String loginId = (String)request.getSession().getAttribute("loginId");
        if( loginId != null ){
            List<MemberEntity> list = memberRepository.findByMemberId(loginId);
            if( list.size() > 0 ){
                MemberEntity memberEntity = list.get(0);
                model.addAttribute("loginName", memberEntity.getMemberName());
            }
        }

        return "/userApp/memberDelete";
    }
    // 4. 더보기 끝 ---------------------------------------------

    // 메장 및 메뉴 검색, 선택
    //메뉴검색
    @GetMapping("/menu/search")
    public String menuSearch() {
        return "/userApp/menu_search";
    }

    //주문하기(메뉴선택)
    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/menu")
    public String orderChoice(Model model) {
        List<ItemEntity> listEntity = itemRepository.findAll();
        List<ItemDto> listDto = listEntity
                .stream()
                .map(ItemDto::toDto)
                .collect(Collectors.toList());

        model.addAttribute("count", listDto.size());
        model.addAttribute("list", listDto);
        return "/userApp/menu";
    }

    // 메뉴 상세 페이지 이동
    @GetMapping("/menu/detail/{name}")
    public String menuDetail(@PathVariable String name, Model model) {

        List<ItemEntity> items = itemRepository.findByItemName(name);
        if (!items.isEmpty()) {
            ItemEntity item = items.get(0);
            model.addAttribute("item", item);
            return "/userApp/oderMenu"; //메뉴 상세페이지로 이동
        } else {
            return "redirect:/menu";
        }
    }

    //매장선택
    @Autowired
    private StoreRepository storeRepository;
    @GetMapping("/store")
    public String storeChoice(Model model) {
        List<StoreEntity> listEntity = storeRepository.findAll();
        List<StoreDto> listDto = listEntity
                .stream()
                .map(StoreDto::toDto)
                .collect(Collectors.toList());

        model.addAttribute("count", listDto.size());
        model.addAttribute("list", listDto);

        return "/userApp/store_choice";
    }

    //매장검색
    @GetMapping("/store/search")
    public String storeSearch() {
        return "/userApp/store_search";
    }
}

