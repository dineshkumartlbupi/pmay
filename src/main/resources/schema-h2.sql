-- PMAY Enterprise Portal Data
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS PMAY_STATE;

CREATE TABLE users (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(500) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users(username)
);

CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);

CREATE TABLE PMAY_STATE (
    STATE_CODE VARCHAR(10) PRIMARY KEY,
    STATE_NAME VARCHAR(100),
    SHORT_NAME VARCHAR(10),
    LGD_STATE_CODE VARCHAR(10),
    ST_LOCAL_NAME VARCHAR(100)
);

-- Primary Dataset (15 Key States)
INSERT INTO PMAY_STATE VALUES ('UP', 'Uttar Pradesh', 'UP', '09', 'उत्तर प्रदेश');
INSERT INTO PMAY_STATE VALUES ('BR', 'Bihar', 'BR', '10', 'बिहार');
INSERT INTO PMAY_STATE VALUES ('MH', 'Maharashtra', 'MH', '27', 'महाराष्ट्र');
INSERT INTO PMAY_STATE VALUES ('WB', 'West Bengal', 'WB', '19', 'पश्चिम बंगाल');
INSERT INTO PMAY_STATE VALUES ('MP', 'Madhya Pradesh', 'MP', '23', 'मध्य प्रदेश');
INSERT INTO PMAY_STATE VALUES ('TN', 'Tamil Nadu', 'TN', '33', 'தமிழ்நாடு');
INSERT INTO PMAY_STATE VALUES ('KA', 'Karnataka', 'KA', '29', 'ಕರ್ನಾಟಕ');
INSERT INTO PMAY_STATE VALUES ('KL', 'Kerala', 'KL', '32', 'കേരളം');
INSERT INTO PMAY_STATE VALUES ('AP', 'Andhra Pradesh', 'AP', '28', 'ఆంధ్ర ప్రदेश్');
INSERT INTO PMAY_STATE VALUES ('GJ', 'Gujarat', 'GJ', '24', 'ગુજરાત');
INSERT INTO PMAY_STATE VALUES ('RJ', 'Rajasthan', 'RJ', '08', 'राजस्थान');
INSERT INTO PMAY_STATE VALUES ('JK', 'Jammu and Kashmir', 'JK', '01', 'जम्मू और कश्मीर');
INSERT INTO PMAY_STATE VALUES ('HR', 'Haryana', 'HR', '06', 'हरियाणा');
INSERT INTO PMAY_STATE VALUES ('PB', 'Punjab', 'PB', '03', 'ਪੰਜਾਬ');

-- Security Account (Login Fix)
INSERT INTO users (username, password, enabled) VALUES ('ifmisuser', '{noop}ifmispassword', true);
INSERT INTO authorities (username, authority) VALUES ('ifmisuser', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('ifmisuser', 'ROLE_ADMIN');
