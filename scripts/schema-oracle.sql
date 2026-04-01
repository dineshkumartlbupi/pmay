-- 1. Connect as 'system' first to create the PMAY user:
-- CREATE USER pmay_user IDENTIFIED BY pmay_password;
-- GRANT CONNECT, RESOURCE, DBA TO pmay_user;
-- 2. Then connect as 'pmay_user' and run the rest of this script:

-- Script for Oracle SQL Developer
-- Create tables for Spring Security (JdbcUserDetailsManager)

CREATE TABLE users (
    username VARCHAR2(50) NOT NULL PRIMARY KEY,
    password VARCHAR2(500) NOT NULL,
    enabled NUMBER(1) NOT NULL
);

CREATE TABLE authorities (
    username VARCHAR2(50) NOT NULL,
    authority VARCHAR2(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users(username)
);

CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);

-- Create table for PMAY State
CREATE TABLE PMAY_STATE (
    STATE_CODE VARCHAR2(10) PRIMARY KEY,
    STATE_NAME VARCHAR2(100),
    SHORT_NAME VARCHAR2(10),
    LGD_STATE_CODE VARCHAR2(10),
    ST_LOCAL_NAME VARCHAR2(100)
);

-- Insert sample data
INSERT INTO PMAY_STATE (STATE_CODE, STATE_NAME, SHORT_NAME, LGD_STATE_CODE, ST_LOCAL_NAME) 
VALUES ('UP', 'Uttar Pradesh', 'UP', '09', 'उत्तर प्रदेश');
INSERT INTO PMAY_STATE (STATE_CODE, STATE_NAME, SHORT_NAME) VALUES ('BR', 'Bihar', 'BR');
INSERT INTO PMAY_STATE (STATE_CODE, STATE_NAME, SHORT_NAME) VALUES ('MP', 'Madhya Pradesh', 'MP');

COMMIT;
