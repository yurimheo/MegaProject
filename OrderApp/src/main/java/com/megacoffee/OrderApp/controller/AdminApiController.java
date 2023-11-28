package com.megacoffee.OrderApp.controller;

import com.megacoffee.OrderApp.dto.ResultDto;
import com.megacoffee.OrderApp.entity.NoticeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminApiController {
    @Autowired
    private NoticeRepository noticeRepository;
    @PostMapping("/notice/deleteSelectedNotice")
    @Transactional
    public ResultDto deleteSelectedMembers(@RequestBody Map<String, List<Integer>> params) {
        List<Integer> noticeNos = params.get("noticeNos");

        try {
            // 회원 아이디에 해당하는 회원을 삭제
            for (int noticeNo : noticeNos ) {
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
}
