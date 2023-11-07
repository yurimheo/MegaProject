DROP DATABASE IF EXISTS megamega; -- 'megamega' DB가 이미 있다면, 삭제함
CREATE DATABASE megamega; -- 'megamega' DB 생성

-- 회원 테이블
DROP TABLE if EXISTS megamega.member;
CREATE TABLE megamega.member (
    member_no INT AUTO_INCREMENT NOT NULL PRIMARY KEY, --고유키
    member_id VARCHAR(255) NOT NULL UNIQUE, --아이디
    member_pw VARCHAR(255) NOT NULL, -- 암호키
    member_name VARCHAR(255) NOT NULL, -- 이름
    member_birth DATETIME DEFAULT NOW() -- 생년월일
    member_email VARCHAR(255) NOT NULL, -- 이메일
    member_phone VARCHAR(255) NOT NULL, -- 연락처
    pw_question VARCHAR(255) NOT NULL, -- 비밀번호 찾기 질문
    pw_answer VARCHAR(255) NOT NULL, -- 비밀번호 찾기 답
    member_role VARCHAR(255) DEFAULT('ROLE_USER') NOT NULL, -- 권한
    member_join_datetime  DATETIME DEFAULT NOW(), -- 가입일
    member_stamp INT, -- 스탬프
    
    member_store_pick INT, --지점 즐겨찾기
    member_item_pick INT -- 나만의 메뉴
    );

-- 상품 테이블
DROP TABLE if EXISTS megamega.item;
CREATE TABLE megamega.item (
item_no INT AUTO_INCREMENT NOT NULL PRIMARY KEY, -- 고유키  
item_code VARCHAR(255) NOT NULL UNIQUE -- 상품코드
item_name VARCHAR(255) NOT NULL --상품명
item_cate VARCHAR(255) NOT NULL -- 카테고리
item_price INT(255) NOT NULL -- 가격
item_recommend INT(255) NOT NULL -- 추천메뉴 (추천메뉴 = 1)
item_new INT(255) NOT NULL -- 신메뉴 (신메뉴 = 1)
item_image_url TEXT NOT NULL -- 이미지
item_update_datetime DATETIME DEFAULT NOW() --상품 등록일
);

-- 제품 영양정보 테이블
CREATE TABLE ITEM_FACTS (
    item_name VARCHAR(255) NOT NULL, -- 상품명
    item_code VARCHAR(255) NOT NULL UNIQUE, -- 상품 코드
    facts_no INT AUTO_INCREMENT PRIMARY KEY, -- 고유키
    kcal INT NOT NULL, -- 칼로리
    na INT NOT NULL, -- 나트륨
    sugar INT NOT NULL, -- 당
    fat INT NOT NULL, -- 포화지방
    protein INT NOT NULL, -- 단백질
    caffeine INT NOT NULL, -- 카페인
    allergy VARCHAR(255) NOT NULL -- 알레르기 유발 성분
);

-- 지점 테이블
DROP TABLE if EXISTS store;
CREATE TABLE store (
	store_name TEXT NOT NULL, -- 지점명
	store_address VARCHAR(255) -- 지점주소
);

-- 장바구니 테이블
DROP TABLE if EXISTS cart;
CREATE TABLE cart (
    cart_no INT AUTO_INCREMENT NOT NULL PRIMARY KEY, -- 장바구니 번호 (고유키)
   cart_code VARCHAR(255) NOT NULL UNIQUE, -- 장바구니 코드(UUID
   item_code VARCHAR(255) NOT NULL UNIQUE, -- 상품 코드(UUID)
    item_name TEXT NOT NULL, -- 상품명
    item_image_url TEXT NOT NULL, -- 이미지
    item_price INT(255) NOT NULL, -- 가격
    cart_item_amount INT(255) NOT NULL, -- 구매갯수
    cart_date DATETIME DEFAULT NOW() -- 주문일시
);

-- 결제 테이블
DROP TABLE if EXISTS `order`;
CREATE TABLE `order` (
 order_no INT AUTO_INCREMENT PRIMARY KEY, -- 고유키
 cart_item_code_1 VARCHAR(255) NOT NULL UNIQUE, -- 상품코드1(UUID)
    cart_item_code_2 VARCHAR(255) UNIQUE, -- 상품코드2(UUID)
    cart_item_code_3 VARCHAR(255) UNIQUE, -- 상품코드3(UUID)
    cart_item_code_4 VARCHAR(255) UNIQUE, -- 상품코드4(UUID)
    cart_item_code_5 VARCHAR(255) UNIQUE, -- 상품코드5(UUID)

 order_total_price INT NOT NULL, -- 주문 총금액
    order_total_count TINYINT NOT NULL, -- 주문 상품 개수(최대 5개)
    -- 주문자/수령자 정보
    order_number INT NOT NULL, -- 주문자 임시번호(0 ~ 999)
    -- 결제방법
    order_pay_type INT DEFAULT(1) NOT NULL, -- 01 신용카드 또는 02 간편결제
    -- 주문상태
    -- 결제완료 -> 주문확인 -> 제조완료
    order_state VARCHAR(255) NOT NULL DEFAULT '결제완료',
    order_datetime DATETIME DEFAULT NOW() -- 결제일시
    );
SELECT * FROM `order`;

DROP TABLE if EXISTS option;
CREATE TABLE option (
 item_cate VARCHAR(255) NOT NULL,
 option_name VARCHAR(255) ,
 option_code VARCHAR(255) NOT NULL UNIQUE ,
 option_price  VARCHAR(255) NOT NULL
    );
SELECT * FROM option;