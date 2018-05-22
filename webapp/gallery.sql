CREATE TABLE gallery(
no NUMBER,
filePath varchar2(100),
orgName varchar2(100),
saveName varchar2(100),
fileSize number,
exName varchar2(20),
regdate DATE,
user_no NUMBER,
PRIMARY KEY(no),
CONSTRAINT c_gallery_fk FOREIGN KEY (user_no)
REFERENCES users(no)
);

SELECT * FROM gallery;

CREATE SEQUENCE SEQ_GALLERY
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCACHE;