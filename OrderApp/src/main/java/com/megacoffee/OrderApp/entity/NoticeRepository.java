package com.megacoffee.OrderApp.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface NoticeRepository extends JpaRepository<NoticeEntity,Long> {
    List<NoticeEntity> findByNoticeCate(String noticeCate);

    void deleteByNoticeNo(long noticeNo);

    List<NoticeEntity> findByNoticeNo(long no);

    Optional<NoticeEntity> findOptionalByNoticeNo(long noticeNo);
}
