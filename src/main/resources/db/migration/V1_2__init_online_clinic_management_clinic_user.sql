create table clinic_user
(
    user_id   SERIAL       NOT NULL,
    user_name TEXT         NOT NULL,
    email     VARCHAR(255) NOT NULL,
    phone     TEXT         NOT NULL,
    password  VARCHAR(255) NOT NULL,
    active    BOOLEAN      NOT NULL,
    primary key (user_id),
    UNIQUE (email)
);