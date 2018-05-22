CREATE TABLE guestbook(
no NUMBER PRIMARY KEY,
name varchar2(80) not null,
password varchar2(20) not null,
content varchar2(2000) not null,
reg_date date not null
);

CREATE SEQUENCE seq_guestbook_no
INCREMENT BY 1
START WITH 1
nocache;

SELECT * FROM GUESTBOOK;

DROP TABLE GUESTBOOK;

declare --선언부
     i number :=1;
begin --실행부
     while i<=997 loop
          insert into guestbook (no,name,password,content,reg_date)
          values
          ((select nvl(max(no)+1,1)from guestbook)
          ,'이름'||i, '비밀번호'||i,'내용'||i, sysdate);
          i:=i+1; --조건
     end loop;
end;

commit;

select * from guestbook;

DROP SEQUENCE seq_guestbook_no;

CREATE SEQUENCE seq_guestbook_no
START WITH 1030
INCREMENT BY 1;
commit;