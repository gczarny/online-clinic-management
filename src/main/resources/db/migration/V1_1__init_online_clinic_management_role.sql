create table role
(
    role_id   SERIAL       NOT NULL,
    role_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (role_id),
    UNIQUE (role_name)
);