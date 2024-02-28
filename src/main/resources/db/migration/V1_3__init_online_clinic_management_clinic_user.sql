create table clinic_user
(
    user_id    SERIAL       NOT NULL,
    user_name  TEXT         NOT NULL,
    email      VARCHAR(255) NOT NULL,
    phone      TEXT         NOT NULL,
    password   VARCHAR(255) NOT NULL,
    active     BOOLEAN      NOT NULL,
    address_id INTEGER      NOT NULL,
    primary key (user_id),
    UNIQUE (email),
    CONSTRAINT fk_clinic_user_address
        FOREIGN KEY (address_id)
            REFERENCES address (address_id)
);