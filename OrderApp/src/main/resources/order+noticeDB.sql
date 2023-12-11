-- 주문 및 공지 데이터
-- 주문 데이터
INSERT INTO megamega.`order` (
 order_no, cart_item_code_1, cart_item_code_2, cart_item_code_3, cart_item_code_4, cart_item_code_5,
 order_total_price, order_total_count, order_number, member_id,  order_pay_type, order_state, order_datetime)
 VALUES( 0, 
 '1b3f1b05-991c-4807-83be-8c30e54257c7',
 '2b3f1b05-991c-4827-83be-8c30e57257c7',
 '3b3f1b45-991c-4867-83be-8c30e57257c7',
 '4b3f1b04-991c-4307-83be-8c30e57257c7',
 '5b3f1b25-991c-4807-83be-8c30e57257c7',
  '3000','1','222','test1','02', 
 '결제완료', DEFAULT
 ),
 ( 0, 
 '2b3f1b05-991c-4807-83be-8c30e54257c7',
 '3b3f1b05-991c-4827-83be-8c30e57257c7',
 '4b3f1b45-991c-4867-83be-8c30e57257c7',
 '5b3f1b04-991c-4307-83be-8c30e57257c7',
 '6b3f1b25-991c-4807-83be-8c30e57257c7',
  '9000','1','262','test2','02', 
 '결제완료', DEFAULT
 ),
 (0, 
 '3b3f1b05-991c-4807-83be-8c30e54257c7',
 '4b3f1b05-991c-4827-83be-8c30e57257c7',
 '5b3f1b45-991c-4867-83be-8c30e57257c7',
 '6b3f1b04-991c-4307-83be-8c30e57257c7',
 '7b3f1b25-991c-4807-83be-8c30e57257c7',
  '4500','1','302','test3','01', 
 '결제완료', DEFAULT
 ),
 (0, 
 '4b3f1b05-991c-4807-83be-8c30e54257c7',
 '5b3f1b05-991c-4827-83be-8c30e57257c7',
 '6b3f1b45-991c-4867-83be-8c30e57257c7',
 '7b3f1b04-991c-4307-83be-8c30e57257c7',
 '8b3f1b25-991c-4807-83be-8c30e57257c7',
  '8000','1','552','test4','02', 
 '결제완료', DEFAULT
 ),
 (0, 
 '5b3f1b05-991c-4807-83be-8c30e54257c7',
 '6b3f1b05-991c-4827-83be-8c30e57257c7',
 '7b3f1b45-991c-4867-83be-8c30e57257c7',
 '8b3f1b04-991c-4307-83be-8c30e57257c7',
 '9b3f1b25-991c-4807-83be-8c30e57257c7',
  '12000','1','615','test5','01', 
 '결제완료', DEFAULT
 );
 INSERT INTO megamega.`order` (
 order_no, cart_item_code_1, cart_item_code_2, cart_item_code_3, cart_item_code_4, cart_item_code_5,
 order_total_price, order_total_count, order_number, member_id,  order_pay_type, order_state, order_datetime)
 VALUES
 (0, 
 '9c1f1b05-921c-4807-83be-8c30e54257c7',
 '9c2f1b05-931c-4827-83be-8c30e57257c7',
 '9c3f1b45-941c-4867-83be-8c30e57257c7',
 NULL,
 NULL,
  '24000','3','616','test5','01', 
 '결제완료', DEFAULT
 );
 
 
-- 공지 데이터
INSERT INTO megamega.notice (
notice_no, notice_title, notice_cate, notice_image_url, notice_title_image,notice_datetime
)VALUES (0,
'[신메뉴 출시] 귀여운 악당 미니언즈의 우당탕탕 Happy MEGA Party',
'공지사항','minions.jpg',
NULL,
DEFAULT),(0,
'[이벤트] 메가MGC커피X미니언즈 시즌 출시 기념 이벤트',
'이벤트','minions2.jpg',
'minionsT.png',
DEFAULT),(0,
'[공지] 손흥민X있지 TVCF 공개! 메가MGC커피와 즐거움이 커진다!',
'공지사항','2b0ebe136ffa8ac3e6464f1d1e273aa5_1694592428_8809.jpg',
'add1.jpg',
DEFAULT),(0,
'[공지] 탄소중립실천포인트제 도입 ',
'공지사항','ff240fd2ef1b1d553ac4e83caf91cec3_1693985748_9515.jpg',
NULL,
DEFAULT),(0,
'[공지] 23년도 하반기 메가MGC커피 슈퍼바이저 신입사원 공개 채용',
'공지사항','bcf6db6b49d092c62baada55b7322379_1687423806_6172.png',
NULL,
DEFAULT),(0,
'[이벤트] 9~12월 메가MGC커피 X 하나페이 적립 EVENT',
'이벤트','21912d925e6a5c03a77ecf716c560f5d_1695622992_5952.jpg',
'thumb-4b30ea60_1695623033_img1_640x0.png',
DEFAULT),(0,
'[이벤트] KT 멤버십 VIP 초이스',
'이벤트','c7a1406acfcb7eae724f14c38d9319ef_1677492556_2471.jpg',
'add4.png',
DEFAULT),(0,
'[이벤트] 겨울시즌 오픈기념 이벤트',
'이벤트','498ade42946e33d43fbf6b323debed21_1698281193_9257.jpg',
'add2.jpg',
DEFAULT);