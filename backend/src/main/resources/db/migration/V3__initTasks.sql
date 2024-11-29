CREATE TABLE tasks
(
    header    VARCHAR(50)  NOT NULL,
    text      VARCHAR(255) NOT NULL,
    user_id   INT          NOT NULL,
    status    BOOLEAN      NOT NULL,
    done_time TIMESTAMP    NULL,
    CONSTRAINT PRIMARY KEY (header),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
