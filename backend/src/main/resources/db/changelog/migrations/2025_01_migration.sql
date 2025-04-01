-- liquibase formatted sql

-- changeset robin.franz:Adding-User
CREATE TABLE STREAM_USER
(
    ID          BIGSERIAL PRIMARY KEY,
    USERNAME    VARCHAR(255),
    EMAIL       VARCHAR(255),
    PASSWORD    VARCHAR(255),
    NAME        VARCHAR(255),
    LAST_NAME   VARCHAR(255),
    USER_TOKEN  VARCHAR(255),
    ADMIN       BOOLEAN
);