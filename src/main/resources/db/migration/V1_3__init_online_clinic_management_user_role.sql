create table user_role
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    primary key (user_id, role_id),
    CONSTRAINT fk_user_role_clinic_user
        FOREIGN KEY (user_id)
            REFERENCES clinic_user (user_id),
    CONSTRAINT fk_user_role_role
        FOREIGN KEY (role_id)
            REFERENCES role (role_id)
);