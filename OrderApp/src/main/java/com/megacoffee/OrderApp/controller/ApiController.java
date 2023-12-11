package com.megacoffee.OrderApp.controller;

import com.megacoffee.OrderApp.ItemRequest;
import com.megacoffee.OrderApp.dto.*;
import com.megacoffee.OrderApp.entity.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Member;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

// @RestController : 해당 클래스가 'RESTfulAPI'를 제공하는 컨트롤러임을 나타내는 어노테이션
@RestController
public class ApiController {
    // 기능 구현
    // <이용자>
    // 1. 로그인 및 회원가입 시작 -------------------------------------
    @Autowired
    private MemberRepository memberRepository;

    @PostMapping("/loginAction")
    public ResultDto loginAction(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        System.out.println("loginId:" + loginDto.getLoginId());
        System.out.println("loginPw:" + loginDto.getLoginPw());
        //로그인 액션 : 아이디, 암호가 DB에 있으면 로그인 성공, 아니면 실패
        List<MemberEntity> list = memberRepository.findByMemberIdAndMemberPw(
                loginDto.getLoginId(),
                loginDto.getLoginPw()
        );
        ResultDto resultDto = null;
        if( list.size() > 0 ) {
            //로그인 성공
            //관리자로 로그인하면
            if( loginDto.getLoginId().equals("admin") ){
                resultDto = ResultDto.builder()
                        .status("ok")
                        .result(2)
                        .build();
            }else{
                resultDto = ResultDto.builder()
                        .status("ok")
                        .result(1)
                        .build();
            }

            request.getSession().setAttribute("loginId", loginDto.getLoginId());
            //request.getSession().invalidate(); //로그아웃처리
        }else{
            //로그인 실패
            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(0)
                    .build();
        }

        return resultDto;
    }

    @PostMapping("/checkIdAction")
    public ResultDto checkIdAction(@RequestBody LoginDto loginDto) {
        try {
            System.out.println("loginId:" + loginDto.getLoginId());
            //로그인 액션 : 아이디 있으면 되돌아가기, 없으면 회원가입 이어서 진행
            List<MemberEntity> list = memberRepository.findByMemberId(
                    loginDto.getLoginId()
            );
            ResultDto resultDto = null;
            if (list.size() > 0) {
                // 아이디가 존재하면 회원가입 불가능
                resultDto = ResultDto.builder()
                        .status("ok")
                        .result(0)
                        .build();
            } else {
                // 아이디가 존재하지 않으면 회원가입 가능
                resultDto = ResultDto.builder()
                        .status("ok")
                        .result(1)
                        .build();
            }
            return resultDto;
        } catch (Exception e) {
            return ResultDto.builder()
                    .status("error")
                    .build();
        }
    }

    @PostMapping("/joinAction")
    public ResultDto joinAction(@RequestBody JoinDto joinDto, Model model) {
        System.out.println("loginId:" + joinDto.getLoginId());
        System.out.println("loginPw:" + joinDto.getLoginPw());
        System.out.println("userName:" + joinDto.getUserName());
        System.out.println("userBirth:" + joinDto.getUserBirth());
        System.out.println("userEmail:" + joinDto.getUserEmail());
        System.out.println("userPhone:" + joinDto.getUserPhone());
        MemberEntity memberJoinEntity = MemberEntity.toJoinEntity(joinDto);
        // 새 멤버 엔티티를 리포지터리에 저장함
        MemberEntity newEntity = memberRepository.save(memberJoinEntity);
        ResultDto resultDto = null;
        if (newEntity != null) {
            //회원가입 성공
            model.addAttribute("memberName", joinDto.getUserName());
            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(1)
                    .build();
        } else {
            //회원가입 실패
            resultDto = ResultDto.builder()
                    .status("ok")
                    .result(0)
                    .build();
        }
        return resultDto;
    }

    // 아이디 및 비번 찾기 - 1. 아이디 찾기 -------------------------
    @PostMapping("/findIdAction")
    public ResultDto findIdAction(@RequestBody FindIdDto findIdDto) {
        try {
            // 이름과 이메일을 이용해 회원을 찾음
            List<MemberEntity> members = memberRepository.findByMemberNameAndMemberEmail(
                    findIdDto.getUserName(),
                    findIdDto.getUserEmail()
            );
            ResultDto resultDto = null;
            if (!members.isEmpty()) {
                // 찾은 회원이 있으면 아이디를 응답에 포함시켜 전송
                MemberEntity foundMember = members.get(0);
                resultDto = ResultDto.builder()
                        .status("ok")
                        .result(1)
                        .data(foundMember.getMemberId())
                        .join_date(foundMember.getMemberJoinDate())
                        .build();
            } else {
                // 찾은 회원이 없으면 실패 응답
                resultDto = ResultDto.builder()
                        .status("ok")
                        .result(0)
                        .build();
            }
            return resultDto;
        } catch (Exception e) {
            // 에러 발생 시 에러 응답
            return ResultDto.builder()
                    .status("error")
                    .build();
        }
    }

    // 아이디 및 비번 찾기 - 2. 비밀번호 찾기 -----------------------
    @PostMapping("/findPwAction")
    public ResultDto findPwAction(@RequestBody FindPasswordDto findPasswordDto, HttpServletRequest request) {
        try {
            String userName = findPasswordDto.getUserName();
            String userEmail = findPasswordDto.getUserEmail();
            String loginId = findPasswordDto.getLoginId();
            // 유효성 검사
            if (userName == null || userEmail == null || loginId == null) {
                return ResultDto.builder()
                        .status("error")
                        .message("유효하지 않은 입력입니다.")
                        .build();
            }
            List<MemberEntity> members = memberRepository.findByMemberNameAndMemberEmailAndMemberId(
                    userName,
                    userEmail,
                    loginId
            );
            ResultDto resultDto;
            if (!members.isEmpty()) {
                MemberEntity foundMember = members.get(0);
                request.getSession().setAttribute("loginId", foundMember.getMemberId());
                resultDto = ResultDto.builder()
                        .status("ok")
                        .result(1)
                        .data(foundMember.getMemberId())
                        .join_date(foundMember.getMemberJoinDate())
                        .build();
            } else {
                resultDto = ResultDto.builder()
                        .status("ok")
                        .result(0)
                        .message("회원을 찾을 수 없습니다.")
                        .build();
            }
            return resultDto;
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return ResultDto.builder()
                    .status("error")
                    .message("에러가 발생했습니다!")
                    .build();
        }
    }

    // 아이디 및 비밀번호 찾기 2-2. 비밀번호 수정 ---------------------
    @PostMapping("/modifyPasswordAction")
    public ResultDto modifyPasswordAction(
            @RequestBody ModifyPasswordDto modifyPasswordDto,
            HttpServletRequest request
    ) {
        try {
            // 아이디 가져오기
            String loginId = (String) request.getSession().getAttribute("loginId");
            // 로그인 아이디로 사용자 정보 조회
            List<MemberEntity> members = memberRepository.findByMemberId(loginId);
            ResultDto resultDto;
            if (!members.isEmpty()) {
                MemberEntity member = members.get(0);
                // 새로운 비밀번호 설정
                member.setMemberPw(modifyPasswordDto.getNewPassword());
                // 비밀번호 업데이트
                memberRepository.save(member);
                resultDto = ResultDto.builder()
                        .status("ok")
                        .result(1)
                        .build();
            } else {
                // 로그인 아이디로 사용자를 찾을 수 없음
                resultDto = ResultDto.builder()
                        .status("ok")
                        .result(0)
                        .build();
            }
            return resultDto;
        } catch (Exception e) {
            // 에러 발생 시 에러 응답
            return ResultDto.builder()
                    .status("error")
                    .build();
        }
    }

    // 계정 설정 - 계정 정보 변경
    // (1) 이름 변경
    @PostMapping("/memberSetting/updateName")
    public ResultDto memberUpdate(Model model, HttpServletRequest request, @RequestBody MemberDto memberDto) {
        String loginId = (String)request.getSession().getAttribute("loginId");

        // 아이디 값으로 회원 정보 찾기
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findOptionalByMemberId(loginId);

        ResultDto resultDto;

        if (optionalMemberEntity.isPresent()) {
            // 기존 상품이 존재할 경우 수정 진행
            MemberEntity existingEntity = optionalMemberEntity.get();

            // 변경하려는 값으로 엔터티 업데이트
            existingEntity.setMemberName(memberDto.getMemberName()); // 회원 이름 변경

            // 엔터티 저장
            MemberEntity updatedEntity = memberRepository.save(existingEntity);

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
    // (2) 핸드폰 번호 변경
    @PostMapping("/memberSetting/updatePhone")
    public ResultDto memberUpdate2(Model model, HttpServletRequest request, @RequestBody MemberDto memberDto) {
        String loginId = (String)request.getSession().getAttribute("loginId");

        // 아이디 값으로 회원 정보 찾기
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findOptionalByMemberId(loginId);

        ResultDto resultDto;

        if (optionalMemberEntity.isPresent()) {
            // 기존 상품이 존재할 경우 수정 진행
            MemberEntity existingEntity = optionalMemberEntity.get();

            // 변경하려는 값으로 엔터티 업데이트
            existingEntity.setMemberPhone(memberDto.getMemberPhone()); // 회원 핸드폰 번호 변경

            // 엔터티 저장
            MemberEntity updatedEntity = memberRepository.save(existingEntity);

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

    // 회원 탈퇴 액션
    @PostMapping("/deleteMemberAction")
    @Transactional
    public ResultDto deleteMemberAction(@RequestBody MemberDto memberDto) {
        try {
            // 아이디 값으로 회원 정보 찾기
            Optional<MemberEntity> optionalMemberEntity = memberRepository.findOptionalByMemberName(memberDto.getMemberName());

            if (optionalMemberEntity.isPresent()) {
                // 기존 회원이 존재할 경우 삭제 진행
                memberRepository.deleteByMemberName(memberDto.getMemberName());

                return ResultDto.builder()
                        .status("ok")
                        .result(1)
                        .build();
            } else {
                // 회원이 존재하지 않는 경우
                return ResultDto.builder()
                        .status("error")
                        .message("삭제할 회원을 찾을 수 없습니다.")
                        .build();
            }
        } catch (Exception e) {
            // 예외가 발생한 경우
            String errorMessage = "삭제 중 오류가 발생했습니다.";
            if (e.getMessage() != null) {
                errorMessage += " 원인: " + e.getMessage();
            }

            return ResultDto.builder()
                    .status("error")
                    .message(errorMessage)
                    .build();
        }
    }


    // 매장 선택
    @Autowired
    StoreRepository storeRepository;
    //매장 검색 기능
    @PostMapping("/user/store")
    public List<StoreDto> store(@RequestBody Map<String, String> params) {
        String searchValue = params.get("searchValue");

        List<StoreEntity> storeList = storeRepository.findByStoreNameContaining(searchValue);

        return storeList.stream()
                .map(StoreDto::toDto)
                .collect(Collectors.toList());

    }

    // 메뉴 선택
    @Autowired
    ItemRepository itemRepository;

    @PostMapping("/menu/select")
    public List<ItemDto> menuSelect(@RequestBody Map<String, String> params){
        String category = params.get("category");

        List<ItemEntity> itemList;

        if ("신메뉴".equals(category)) {
            // 신메뉴 카테고리 선택한 경우
            itemList = itemRepository.findByItemNew(1);
        }else {
            // 타 카테고리 선택한 경우 ( 커피, 에이드 & 스무디, 티 )
            itemList = itemRepository.findByItemCate(category);
        }

        return itemList.stream()
                .map(ItemDto::toDto)
                .collect(Collectors.toList());

    }

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;

    //바로주문하기
    //메뉴상세 페이지에서 바로기능 누르면 DB에 저장하는 기능
    @PostMapping("/updateOrder")
    public ResultDto updateOrder(@RequestBody CartDto cartDto) {

        CartEntity cartEntity = CartEntity.toEntity(cartDto);

        CartEntity newEntity = cartRepository.save(cartEntity);

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

    //장바구니 담기
    //메뉴상세 페이지에서 바로기능 누르면 DB에 저장하는 기능
    @PostMapping("/updateCart")
    public ResultDto updateCart(@RequestBody CartDto cartDto) {

        CartEntity cartEntity = CartEntity.toEntity(cartDto);

        CartEntity newEntity = cartRepository.save(cartEntity);

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


    // 카트목록 삭제
    @PostMapping("/cart/delete")
    @Transactional
    public ResultDto deleteCart(@RequestBody ItemRequest request) {
        try {
            String itemName = request.getItemName();

            cartRepository.deleteByItemName(itemName);

            return ResultDto.builder()
                    .status("ok")
                    .result(1)
                    .build();
        } catch (Exception e) {
            return ResultDto.builder()
                    .status("error")
                    .message("삭제 중 오류가 발생했습니다.")
                    .build();
        }
    }

    //주문하기
    @PostMapping("/cart/update")
    public ResultDto updateCartItem(@RequestBody ItemRequest request) {
        try {
            String itemName = request.getItemName();
            int amount = request.getAmount();

            cartRepository.updateItemAmount(itemName, amount);

            return ResultDto.builder()
                    .status("ok")
                    .result(1)
                    .build();
        } catch (Exception e) {
            return ResultDto.builder()
                    .status("error")
                    .message("수량 업데이트 중 오류가 발생했습니다.")
                    .build();
        }
    }

    @PostMapping("/order/update")
    @Transactional
    public ResponseEntity<ResultDto> updateOrder(@RequestBody OrderDto request) {
        try {
            OrderEntity orderEntity = OrderEntity.toOrderEntity(request);
            OrderEntity savedOrder = orderRepository.save(orderEntity);

            if (savedOrder != null) {
                String memberId = request.getMemberId();

                List<MemberEntity> members = memberRepository.findAllByMemberId(memberId);

                for (MemberEntity member : members) {
                    member.setMemberStamp(member.getMemberStamp() + 1); // 스탬프 증가
                    memberRepository.save(member); // 변경된 스탬프 저장
                }
                cartRepository.deleteAll();
                return ResponseEntity.ok()
                        .body(ResultDto.builder()
                                .status("ok")
                                .result(1)
                                .build());
            } else {
                return ResponseEntity.badRequest()
                        .body(ResultDto.builder()
                                .status("error")
                                .message("주문 저장 중 오류가 발생했습니다.")
                                .build());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultDto.builder()
                            .status("error")
                            .message("주문 저장 중 오류가 발생했습니다.")
                            .build());
        }
    }

    
}
