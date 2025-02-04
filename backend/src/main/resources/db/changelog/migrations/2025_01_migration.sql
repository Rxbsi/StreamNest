-- liquibase formatted sql

-- changeset robin.franz:Adding-User
CREATE TABLE STREAM_USER
(
    ID        BIGSERIAL PRIMARY KEY,
    USERNAME  VARCHAR(255),
    EMAIL     VARCHAR(255),
    PASSWORD  VARCHAR(255),
    NAME      VARCHAR(255),
    LAST_NAME VARCHAR(255),
    ADMIN     BOOLEAN
);

-- changeset robin.franz:add-password
ALTER TABLE STREAM_USER
    ADD COLUMN IF NOT EXISTS PASSWORD VARCHAR(255);

-- changeset robin.franz:user-password-token
ALTER TABLE STREAM_USER
    ADD COLUMN IF NOT EXISTS USER_TOKEN VARCHAR(255);