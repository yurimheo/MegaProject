DROP DATABASE IF EXISTS megamega; -- 'megamega' DB가 이미 있다면, 삭제함
CREATE DATABASE megamega; -- 'megamega' DB 생성
ALTER DATABASE megamega CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE member CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE member MODIFY member_name VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
-- 회원 테이블
DROP TABLE if EXISTS megamega.member;
CREATE TABLE megamega.member (
    member_no INT AUTO_INCREMENT NOT NULL PRIMARY KEY, -- 고유키
    member_id VARCHAR(255) NOT NULL UNIQUE, -- 아이디
    member_pw VARCHAR(255) NOT NULL, -- 암호키
    member_name VARCHAR(255) NOT NULL, -- 이름
    member_birth DATE DEFAULT NULL, -- 생년월일
    member_email VARCHAR(255) NOT NULL, -- 이메일
    member_phone VARCHAR(20) NOT NULL, -- 연락처
    member_role VARCHAR(255) DEFAULT('ROLE_USER') NOT NULL, -- 권한
    member_join_date DATE DEFAULT NOW(), -- 가입일
    member_stamp INT -- 스탬프
    );
   
SELECT*FROM megamega.member;

-- 상품 테이블
DROP TABLE if EXISTS megamega.item;
CREATE TABLE megamega.item (
item_no INT AUTO_INCREMENT NOT NULL PRIMARY KEY, -- 고유키  
item_code VARCHAR(255) NOT NULL UNIQUE, -- 상품코드
item_name VARCHAR(255) NOT NULL, -- 상품명
item_cate VARCHAR(255) NOT NULL, -- 카테고리
item_price INT NOT NULL, -- 가격
item_recommend INT DEFAULT(0) NOT NULL, -- 추천메뉴 (추천메뉴 = 1)
item_new INT DEFAULT(0) NOT NULL, -- 신메뉴 (신메뉴 = 1)
item_image_url TEXT NOT NULL, -- 이미지
item_explanation TEXT NOT NULL,
item_update_datetime DATETIME DEFAULT NOW() -- 상품 등록일
);
SELECT * FROM megamega.item;

-- 지점 테이블
DROP TABLE if EXISTS megamega.store;
CREATE TABLE megamega.store (
   store_no INT AUTO_INCREMENT NOT NULL PRIMARY KEY, -- 고유키  
   store_local TEXT NOT NULL, -- 지역
    store_name TEXT NOT NULL, -- 지점명
    store_address VARCHAR(255) -- 지점주소
);

-- 장바구니 테이블
DROP TABLE if EXISTS megamega.cart;
CREATE TABLE megamega.cart (
    cart_no INT AUTO_INCREMENT NOT NULL PRIMARY KEY, -- 장바구니 번호 (고유키)
   cart_code VARCHAR(255) NOT NULL UNIQUE, -- 장바구니 코드(UUID
   item_code VARCHAR(255) NOT NULL UNIQUE, -- 상품 코드(UUID)
    item_name TEXT NOT NULL, -- 상품명
    item_image_url TEXT NOT NULL, -- 이미지
    item_price INT(255) NOT NULL, -- 가격
    cart_item_amount INT(255) NOT NULL, -- 구매갯수
    cart_date DATETIME DEFAULT NOW() -- 주문일시
);
INSERT INTO cart VALUES (
 0, '8b3f1h05-991c-4807-83be-8c30e54257c7', '999a9999-b888-77c7-d666-89894444i885',
 '사과유자차', 'appletea.png',
 '3500','2', DEFAULT);

-- 결제 테이블
DROP TABLE if EXISTS `order`;
CREATE TABLE megamega.`order` (
 order_no INT AUTO_INCREMENT PRIMARY KEY, -- 고유키
 cart_item_code_1 VARCHAR(255) NOT NULL, -- 상품코드1(UUID)
    cart_item_code_2 VARCHAR(255), -- 상품코드2(UUID)
    cart_item_code_3 VARCHAR(255), -- 상품코드3(UUID)
    cart_item_code_4 VARCHAR(255), -- 상품코드4(UUID)
    cart_item_code_5 VARCHAR(255), -- 상품코드5(UUID)

 order_total_price INT NOT NULL, -- 주문 총금액
    order_total_count TINYINT NOT NULL, -- 주문 상품 개수(최대 5개)
    -- 주문자/수령자 정보
     order_number INT NOT NULL, -- 주문자 임시번호(0 ~ 999)
    member_id VARCHAR(255) NOT NULL, -- 
    -- 결제방법
    order_pay_type INT DEFAULT(1) NOT NULL, -- 01 신용카드 또는 02 간편결제
    -- 주문상태
    -- 결제완료 -> 주문확인 -> 제조완료
    order_state VARCHAR(255) NOT NULL DEFAULT '결제완료',
    order_datetime DATETIME DEFAULT NOW() -- 결제일시
    );
SELECT * FROM `order`;

-- 공지 테이블
DROP TABLE if EXISTS megamega.notice;
CREATE TABLE notice (
    notice_no INT AUTO_INCREMENT PRIMARY KEY, -- 고유키
notice_title VARCHAR(255) NOT NULL, -- 공지 이름
notice_cate  VARCHAR(255) NOT NULL, -- 공지 카테고리
notice_image_url TEXT NOT NULL, -- 이미지
notice_title_image TEXT,-- 타이틀이미지
notice_datetime DATETIME DEFAULT NOW() -- 공지일시
);
