create table users(
no number, --�ĺ���
name varchar2(80),
email varchar2(100) unique not null, --�ٲ�� ������ ��������� �־ unique
password varchar2(20) not null,
gender varchar2(10),
primary key(no)
)
--���� ���̺��� sqeunce�� ���� ���ڸ� �ϳ��ϳ� �� �� ���
--��ȣǥ�ε� �̸��� seq_user_no  ����� .nextvar�ϸ� ��ȣǥ�� �ϳ��� �ö� 
create sequence seq_user_no  
increment by 1
start with 1
nocache;

insert into users values(
SEQ_USER_NO.NEXTVAL,
'������',
'leejiyoung514@naver.com',
'1234',
'male'
);

select * from users;