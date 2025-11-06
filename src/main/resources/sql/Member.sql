CREATE DATABASE IF NOT EXISTS task;

USE task;

-- DROP TABLE IF EXISTS member;

CREATE TABLE member (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at DATETIME DEFAULT NULL
);

-- 계정 생성 (비밀번호 Bcrypt 해싱값 이므로 웹으로 회원가입 필요)
INSERT INTO member (email, password, name, role) VALUES ('test1@naver.com', '$2a$12$rFORoITCw3e//NfTnp.Q4uI5BVUjykvB35ESMwTDFaY9cVmUcLUyK', '테스트', 'ROLE_MEMBER');

-- 계정 조회
SELECT *
FROM member;

-- 이메일로 계정 조회
SELECT *
FROM member
WHERE email = 'test1@naver.com';

-- 이메일 중복 확인
SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END
FROM member
WHERE email = 'test1@naver.com';