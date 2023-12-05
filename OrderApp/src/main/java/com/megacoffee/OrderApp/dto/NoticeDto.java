package com.megacoffee.OrderApp.dto;

import com.megacoffee.OrderApp.entity.NoticeEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDto {
    private long noticeNo;
    private String noticeTitle;
    private String noticeCate;
    private String noticeImgUrl;
    private String noticeTitleImg;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime noticeDateTime;

    public static NoticeDto toDto(NoticeEntity entity){
        return NoticeDto.builder()
                .noticeNo(entity.getNoticeNo())
                .noticeTitle(entity.getNoticeTitle())
                .noticeCate(entity.getNoticeCate())
                .noticeImgUrl(entity.getNoticeImgUrl())
                .noticeTitleImg(entity.getNoticeTitleImg())
                .noticeDateTime(entity.getNoticeDateTime())
                .build();
    }
}
