CREATE TABLE tasks
(
    header  VARCHAR(50)  NOT NULL,
    text    VARCHAR(255) NOT NULL,
    userId INT          NOT NULL,
    isDone  BOOLEAN      NOT NULL,
    time    TIMESTAMP    NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (header)
);
