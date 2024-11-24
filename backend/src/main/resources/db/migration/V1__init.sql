CREATE TABLE users
(
    id       INT AUTO_INCREMENT NOT NULL,
    username VARCHAR(255)       NOT NULL,
    password VARCHAR(250)       NOT NULL,
    email    VARCHAR(50)        NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
);