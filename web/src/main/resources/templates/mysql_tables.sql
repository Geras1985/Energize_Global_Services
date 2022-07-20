USE hotel;

DROP TABLE IF EXISTS address, bank, cards, hotel_entity, roles, user_roles, users;

CREATE TABLE address
(
    Id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id     BIGINT,
    created     DATETIME,
    status      VARCHAR(255),
    updated     DATETIME,
    city        VARCHAR(80),
    country     VARCHAR(80),
    postal_code VARCHAR(20),
    reg_address VARCHAR(80),
    res_address VARCHAR(80),
    FOREIGN KEY (user_id) REFERENCES users (Id) ON DELETE CASCADE
);

CREATE TABLE bank
(
    Id      BIGINT PRIMARY KEY AUTO_INCREMENT,
    created DATETIME,
    status  VARCHAR(255),
    updated DATETIME
);

CREATE TABLE card
(
    Id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    payment_mode VARCHAR(31),
    user_id      BIGINT,
    created      DATETIME,
    status       VARCHAR(255),
    updated      DATETIME,
    amount       DOUBLE,
    card_number  VARCHAR(255),
    name         VARCHAR(255),
    card_type    VARCHAR(255),
    cvv          INT,
    valid_date2  DATE,
    FOREIGN KEY (user_id) REFERENCES users (Id) ON DELETE CASCADE
);

CREATE TABLE hotel_entity
(
    Id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id     BIGINT,
    created     DATETIME,
    status      VARCHAR(255),
    updated     DATETIME,
    hotel_email VARCHAR(255),
    hotel_name  VARCHAR(255),
    hotel_phone VARCHAR(13),
    FOREIGN KEY (user_id) REFERENCES users (Id)
);

CREATE TABLE roles
(
    Id      BIGINT PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(100),
    created TIMESTAMP,
    updated TIMESTAMP,
    status  VARCHAR(255)
);

CREATE TABLE users
(
    Id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    username   VARCHAR(100) UNIQUE,
    email      VARCHAR(100) UNIQUE,
    first_name VARCHAR(100),
    last_name  VARCHAR(100),
    password   VARCHAR(255),
    created    TIMESTAMP,
    updated    TIMESTAMP,
    status     VARCHAR(255) DEFAULT VALUE IS TRUE
);

INSERT INTO users(Id, username, email, first_name, last_name, password, created, status)
VALUES (1, 'admin','admin@gmail.com', 'Admin', 'Adminyan', 'admin', current_date,'ACTIVE');

INSERT INTO users(Id, username, email, first_name, last_name, password, created, status)
VALUES (2, 'user','user@gmail.com', 'User', 'Useryan', 'user', current_date,'ACTIVE');

INSERT INTO address(Id, user_id)
VALUES (1, 1);
INSERT INTO address(Id, user_id)
VALUES (2, 2);