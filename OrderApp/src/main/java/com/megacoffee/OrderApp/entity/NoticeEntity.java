package com.megacoffee.OrderApp.entity;

import com.megacoffee.OrderApp.dto.NoticeDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name="notice")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_no")
    private long noticeNo;
    @Column(name = "notice_title")
    private String noticeTitle;
    @Column(name = "notice_cate")
    private String noticeCate;
    @Column(name = "notice_image_url")
    private String noticeImgUrl;
    @Column(name = "notice_title_image")
    private String noticeTitleImg;
    @Column(name = "notice_datetime")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime noticeDateTime;

    public static NoticeEntity toEntity(NoticeDto dto){
        return NoticeEntity.builder()
                .noticeNo(dto.getNoticeNo())
                .noticeTitle(dto.getNoticeTitle())
                .noticeCate(dto.getNoticeCate())
                .noticeImgUrl("images/add/" + dto.getNoticeImgUrl())
                .noticeDateTime(dto.getNoticeDateTime())
                .build();
    }
}
