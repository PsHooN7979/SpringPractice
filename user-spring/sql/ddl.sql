데이터 정의어(DDL : Data Definition Language)

[테이블 생성 및 삭제]
create table users
(
 index bigint generated by default as identity,
 id varchar(255) not null,
 password varchar(255) not null,
 primary key (index)
);
drop table users;

[데이터 삽입]
INSERT INTO users (id, password) VALUES ('user1', 'password1');
INSERT INTO users (id, password) VALUES ('user2', 'password2');
INSERT INTO users (id, password) VALUES ('user3', 'password3');

[데이터 삭제]
DELETE FROM users
DELETE FROM users WHERE id = 'test';

[데이터 조회]
SELECT * FROM users;
