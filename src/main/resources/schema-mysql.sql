DROP DATABASE IF EXISTS campusdb;
CREATE DATABASE campusdb;
USE campusdb;

DROP TABLE IF EXISTS CAMPUS;
DROP TABLE IF EXISTS CLASSROOM;

CREATE TABLE CAMPUS (
                        CAMPUS_NAME VARCHAR(255) PRIMARY KEY,
                        ADDRESS VARCHAR(255),
                        NUMBER_OF_PARKING_SPACES INT,
                        NUMBER_OF_ROOMS INT
);

CREATE TABLE ROOM (
                      ROOM_ID BIGINT PRIMARY KEY AUTO_INCREMENT,
                      NAME VARCHAR(255),
                      TYPE VARCHAR(255),
                      CAPACITY INT,
                      FIRST_NAME VARCHAR(255),
                      LAST_NAME VARCHAR(255),
                      FLOOR VARCHAR(255),
                      CAMPUS_NAME VARCHAR(255),
                      CONSTRAINT FK_ROOM_CAMPUS
                          FOREIGN KEY(CAMPUS_NAME) REFERENCES CAMPUS(CAMPUS_NAME)
)