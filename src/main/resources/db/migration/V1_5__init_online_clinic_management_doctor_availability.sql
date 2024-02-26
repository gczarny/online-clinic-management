create table doctor_availability
(
    doctor_availability_id SERIAL      NOT NULL,
    available_from         TIME        NOT NULL,
    available_until        TIME        NOT NULL,
    date_range_start       DATE        NOT NULL,
    date_range_end         DATE        NOT NULL,
    status                 VARCHAR(50) NOT NULL,
    doctor_id              INT         NOT NULL,
    primary key (doctor_availability_id),
    CONSTRAINT fk_doctor_availability_doctor
        FOREIGN KEY (doctor_id)
            REFERENCES doctor (doctor_id)
);
