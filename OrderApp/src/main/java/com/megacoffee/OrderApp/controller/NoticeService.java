package com.megacoffee.OrderApp.controller;

import com.megacoffee.OrderApp.entity.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    //NoticeRepository 주입
    @Autowired
    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }
    //공지 갯수 가져오기
    public long getNoticeCount() {
        return noticeRepository.count();
    }
}
