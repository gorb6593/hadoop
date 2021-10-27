#신상품삽입
INSERT INTO TB_PRODUCT(PRD_NO, PRD_NM, CATEGORY_NO, PRD_DES, SELL_PRC_UNIT, SELL_START_DT, SELL_END_DT, DLV_CONSTRAINT, AS_FLG, RETURN_FLG, IMG_ORG_FILE_NM, IMG_GEN_FILE_NM, REG_ID, REG_DTM) 
VALUES('PRD000000026', '리본원피스', 'CAT000000001', '리본장식귀여워', 39800, '20210201', '20210201', '국내 배송만 됩니다.', 'Y', 'Y',  'dress_image6.gif', 'dress_image6.gif', 'admin', to_char(sysdate, 'YYYYMMDDHH24MISS'))  ;
INSERT INTO TB_PRODUCT(PRD_NO, PRD_NM, CATEGORY_NO, PRD_DES, SELL_PRC_UNIT, SELL_START_DT, SELL_END_DT, DLV_CONSTRAINT, AS_FLG, RETURN_FLG, IMG_ORG_FILE_NM, IMG_GEN_FILE_NM, REG_ID, REG_DTM) 
VALUES('PRD000000027', '체크스커트', 'CAT000000003', '체크가이뻐요', 39800, '20210201', '20210201', '국내 배송만 됩니다.', 'Y', 'Y',  'bottom_image6.gif', 'bottom_image6.gif', 'admin', to_char(sysdate, 'YYYYMMDDHH24MISS'))  ;
INSERT INTO TB_PRODUCT(PRD_NO, PRD_NM, CATEGORY_NO, PRD_DES, SELL_PRC_UNIT, SELL_START_DT, SELL_END_DT, DLV_CONSTRAINT, AS_FLG, RETURN_FLG, IMG_ORG_FILE_NM, IMG_GEN_FILE_NM, REG_ID, REG_DTM) 
VALUES('PRD000000028', '리본블라우스', 'CAT000000002', '도트무늬', 39800, '20210201', '20210201', '국내 배송만 됩니다.', 'Y', 'Y',  'top_image6.jpg', 'top_image6.jpg', 'admin', to_char(sysdate, 'YYYYMMDDHH24MISS'))  ;


#member테이블 수정
alter table member
add (info varchar2(20));

update member
set info='normal';

commit;

#admin삽입
insert into member values('admin','1234','관리자','본사',100000,'200',null,'admin');
commit;