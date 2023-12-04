DROP DATABASE IF EXISTS megamega; -- 'megamega' DB가 이미 있다면, 삭제함
megamegaCREATE DATABASE megamega; -- 'megamega' DB 생성
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
INSERT INTO megamega.member (
    member_id,
    member_pw,
    member_name,
    member_birth,
    member_email,
    member_phone,
    member_role,
    member_join_date,
    member_stamp
) VALUES 
(
    'test1',
    '1111',
    '테스트1',
    '1990-05-15',
    'test1@example.com',
    '01011112222',
    'ROLE_USER',
    '2023-12-04',
    0
),
(
    'test2',
    '2222',
    '테스트2',
    '1988-08-25',
    'test2@example.com',
    '01022223333',
    'ROLE_USER',
    '2023-12-05',
    0
),
(
    'test3',
    '3333',
    '테스트3',
    '1987-07-27',
    'test3@example.com',
    '01033334444',
    'ROLE_USER',
    '2023-10-11',
    0
),
(
    'test4',
    '4444',
    '테스트4',
    '1994-04-20',
    'test4@example.com',
    '01044445555',
    'ROLE_USER',
    '2022-11-12',
    0
),
(
    'test5',
    '5555',
    '테스트5',
    '2005-05-10',
    'test5@example.com',
    '01055556666',
    'ROLE_USER',
    '2021-09-18',
    0
);

    
SELECT*FROM megamega.member;

-- 지점 즐겨찾기 테이블
DROP TABLE if EXISTS megamega.storepick;
CREATE TABLE storepick (
storepick_no INT AUTO_INCREMENT NOT NULL PRIMARY KEY, -- 고유키
member_store_pick INT, -- 지점 즐겨찾기 (0, 1)
store_name TEXT NOT NULL -- 지점명
);

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
INSERT INTO megamega.item (
item_code,
item_name,
item_cate,
item_price,
item_recommend,
item_new,
item_image_url,
item_explanation,
item_update_datetime
) VALUES(
'999a9999-b888-77c7-d666-555544443333', 
'코코넛커피 스무디',
'에이드&스무디', 
4800, 
0,
1, 
'coconutcoffee.png',
"바삭하고 고소한 코코넛 칩을 올리고 쌉싸름한 커피와 달콤한 코코넛이 조화로운 스무디",
DEFAULT
),
(
'919a1999-b888-77c7-d666-5552441459i9', 
'화이트 뱅쇼',
'티', 
4800, 
0,
1, 
'whitebang.png',
"레몬, 자몽, 석류, 백포도, 사과 등 다양한 과일로 맛을 낸 겨울한정 화이트 뱅쇼",
DEFAULT
),
(
'999a9199-b888-77c7-d666-555544448885', 
'복숭아아이스티',
'티', 
4800, 
1,
0, 
'peachicetea.png',
"홍차의 깊은 맛과 풍부한 복숭아 향이 어우러진 달콤한 여름철 인기 음료",
DEFAULT
),
(
'999a9999-b888-77c7-d666-4u5524448885', 
'유자차',
'티', 
3300, 
0,
0, 
'ujatea.png',
"비타민이 가득 든 상큼달콤한 유자를 듬뿍 넣어 향긋한 즐거움을 전하는 과일티",
DEFAULT
),
(
'999a9999-b888-77c7-d666-4u5544448885', 
'레몬차',
'티', 
3300, 
0,
0, 
'lemontea.png',
"새콤한 레몬의 맛과 향을 오롯이 살린 과일티",
DEFAULT
),
(
'999a9666-b888-77c7-d666-555544448885', 
'자몽차',
'티', 
3300, 
0,
0, 
'fruittea.png',
"달콤쌉싸름한 자몽의 조화로운 맛을 한 잔 가득 느낄 수 있는 과일티",
DEFAULT
),
(
'999a8799-b888-77c7-d666-555544448885', 
'캐모마일',
'티', 
2500, 
0,
0, 
'camomile.png',
"특유의 풀내음을 통해 마음을 진정 시켜주는 마일드한 허브차",
DEFAULT
),
(
'999a9999-b888-77c7-e6e6-555544448885', 
'페퍼민트',
'티', 
2500, 
0,
0, 
'pepermint.png',
"입안 가득 깔끔한 청량감으로 기분까지 상쾌한 허브차",
DEFAULT
),
(
'999a9999-b888-77c7-f66f-5555444f8885', 
'얼그레이',
'티', 
2500, 
0,
0, 
'grey.png',
"홍차의 진한 향과 본연의 부드러움을 느낄 수 있는 허브차",
DEFAULT
),
(
'999a9999-b888-77c7-d666-89894444i885', 
'사과유자차',
'티', 
3500, 
1,
1, 
'appletea.png',
"애플티의 향긋함과 유자청의 상큼함이 어우러진 과일티",
DEFAULT
),
(
'9u9a9999-b888-77c7-d666-555544448885', 
'허니자몽블랙티',
'티', 
3700, 
1,
0, 
'honeyfruitblacktea.png',
"달콤한 꿀청에 재운 자몽에 홍차의 부드러움을 어우른 상큼한 과일티",
DEFAULT
),
(
'9c9a9ff9-b888-77c7-d666-555544448885', 
'메가리카노',
'커피', 
3000, 
0,
0, 
'megaricano.png',
"1L 메가 급 아메리카노",
DEFAULT
),
(
'999ac9f9-b8h8-77c7-d666-555544448885', 
'아메리카노',
'커피', 
2000, 
1,
0, 
'americano.png',
"에스프레소에 물을 혼합한 커피 메가MGC커피의 부드럽고 풍부한 바디감을 느낄 수 있는 메가MGC커피 대표 음료",
DEFAULT
),
(
'999a9999-b888-77c7-d666-5555ha44l225', 
'할메가커피',
'커피', 
1900, 
0,
0, 
'halmgc.png',
"우리 할머니께서 즐겨드시던 달달한 믹스 커피 스타일로 만든 메가MGC커피만의 시원한 커피 음료",
DEFAULT
),
(
'999a9999-b888-77c7-d666-555544448885', 
'왕할메가커피',
'커피', 
2900, 
1,
1, 
'kinghalmgc.png',
"우리 할머니께서 즐겨드시던 달달한 믹스 커피 스타일로 만든 메가MGC커피만의 메가사이즈 커피 음료",
DEFAULT
),
(
'999ah927-q888-77c7-d666-555q44448885', 
'꿀아메리카노',
'커피', 
2700, 
0,
0, 
'honeyAmericano.png',
"아메리카노의 묵직한 바디감에 달콤한 사양벌꿀이 소프트하게 어우러진 커피",
DEFAULT
),
(
'999ah927-q888-77c7-d6e6-5e5q44118885', 
'콜드브루 오리지널',
'커피', 
3500, 
0,
0, 
'coldbrue.png',
"차가운 물에 장시간 우려내 깔끔한 목넘김을 느낄 수 있는 콜드브루",
DEFAULT
),
(
'999ah927-q888-66c7-d666-555q44448885',  
'콜드브루 라떼',
'커피', 
4000, 
0,
0, 
'coldbrueLatte.png',
"콜드브루에 고소한 우유를 섞어, 깔끔함과 부드러움을 잡은 콜드브루 라떼",
DEFAULT
),
(
'999am9oc-ca88-77c7-d116-555546648885', 
'카페모카',
'커피', 
3900, 
0,
0, 
'cafemoca.png',
"초코를 만나 풍부해진 에스프레소와 고소한 우유, 부드러운 휘핑크림까지 더해 달콤하게 즐기는 커피",
DEFAULT
);
SELECT * FROM megamega.item;

-- 지점 테이블
DROP TABLE if EXISTS megamega.store;
CREATE TABLE store (
    store_no INT AUTO_INCREMENT NOT NULL PRIMARY KEY, -- 고유키  
	store_name TEXT NOT NULL, -- 지점명
	store_address VARCHAR(255) -- 지점주소
);

-- 장바구니 테이블
DROP TABLE if EXISTS megamega.cart;
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
INSERT INTO cart VALUES (
 0, '8b3f1h05-991c-4807-83be-8c30e54257c7', '8b3s1b05-991c-4807-83be-8c30e54257c7',
 '초코 뭐시기', 'https://megacoffee.coconutatelier.com/images/item/thumb-102556_main_320x0.png',
 '150000','2', DEFAULT);

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
    member_id VARCHAR(255) NOT NULL UNIQUE, -- 
    -- 결제방법
    order_pay_type INT DEFAULT(1) NOT NULL, -- 01 신용카드 또는 02 간편결제
    -- 주문상태
    -- 결제완료 -> 주문확인 -> 제조완료
    order_state VARCHAR(255) NOT NULL DEFAULT '결제완료',
    order_datetime DATETIME DEFAULT NOW() -- 결제일시
    );
SELECT * FROM `order`;
INSERT INTO `order` VALUES (
 0, '1b3f1b05-991c-4807-83be-8c30e57257c7','2b3f1b05-991c-4807-83be-8c30e57257c7',
 '3b3f1b05-991c-4807-83be-8c30e57257c7','4b3f1b05-991c-4807-83be-8c30e57257c7',
 '5b3f1b05-991c-4807-83be-8c30e57257c7',
  '1000','1','122','TEST2','02', 
 '결제완료', DEFAULT);

-- 공지 테이블
DROP TABLE if EXISTS megamega.notice;
CREATE TABLE notice (
    notice_no INT AUTO_INCREMENT PRIMARY KEY, -- 고유키
notice_title VARCHAR(255) NOT NULL, -- 공지 이름
notice_cate  VARCHAR(255) NOT NULL, -- 공지 카테고리
notice_image_url TEXT NOT NULL, -- 이미지
notice_datetime DATETIME DEFAULT NOW() -- 공지일시
);
