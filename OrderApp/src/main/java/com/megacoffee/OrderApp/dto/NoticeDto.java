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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime noticeDatetime;

    public static NoticeDto fromEntity(NoticeEntity entity){
        return NoticeDto.builder()
                .noticeNo(entity.getNoticeNo())
                .noticeTitle(entity.getNoticeTitle())
                .noticeCate(entity.getNoticeCate())
                .noticeImgUrl(entity.getNoticeImgUrl())
                .noticeDatetime(entity.getNoticeDateTime())
                .build();
    }
}
