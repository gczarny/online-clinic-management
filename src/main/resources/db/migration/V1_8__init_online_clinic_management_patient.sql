create table patient
(
    patient_id SERIAL       NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    pesel      VARCHAR(11)  NOT NULL,
    user_id    INT          NOT NULL,
    primary key (patient_id),
    UNIQUE (pesel),
    CONSTRAINT fk_patient_clinic_user
        FOREIGN KEY (user_id)
            REFERENCES clinic_user (user_id)
);