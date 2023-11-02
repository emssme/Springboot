USE edumon;

CREATE TABLE euser(
	id INT PRIMARY KEY AUTO_INCREMENT,
	NAME VARCHAR(20) NOT NULL,				-- 아이디
	password VARCHAR(300) NOT NULL,
	userName VARCHAR(50) NOT NULL,		-- 이름
	email VARCHAR(100) NOT NULL,
	address VARCHAR(300),
	tel VARCHAR(20),
	regdate DATETIME DEFAULT CURRENT_TIMESTAMP,
	lev VARCHAR(20) DEFAULT 'USER',		/* USER:회원, ADMIN: 관리자, EMP: 직원*/
	act VARCHAR(20) DEFAULT 'JOIN',		/* JOIN: 활동 중, DORMANT: 휴면, WITHDRAW: 탈퇴*/
	CONSTRAINT KEY_name UNIQUE(NAME)		/*아이디 중복x*/
	);
	
DESC euser;
INSERT INTO euser VALUES (DEFAULT, 'kimbk', '1234', '김보경', 'kimbk@edu.com', NULL, NULL, DEFAULT, DEFAULT, DEFAULT);
INSERT INTO euser VALUES (DEFAULT, 'leebk', '1234', '이보경', 'leebk@edu.com', NULL, NULL, DEFAULT, DEFAULT, DEFAULT);
INSERT INTO euser VALUES (DEFAULT, 'parkbk', '1234', '박보경', 'parkbk@edu.com', NULL, NULL, DEFAULT, DEFAULT, DEFAULT);
INSERT INTO euser VALUES (DEFAULT, 'admin', '1234', '관리자', 'admin@edu.com', NULL, NULL, DEFAULT, DEFAULT, DEFAULT);

COMMIT;
SELECT * from euser;
