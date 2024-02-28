create table doctor
(
    doctor_id      SERIAL       NOT NULL,
    first_name     VARCHAR(255) NOT NULL,
    last_name      VARCHAR(255) NOT NULL,
    specialization VARCHAR(255) NOT NULL,
    user_id        INT          NOT NULL,
    primary key (doctor_id),
    CONSTRAINT fk_doctor_clinic_user
        FOREIGN KEY (user_id)
            REFERENCES clinic_user (user_id)
);