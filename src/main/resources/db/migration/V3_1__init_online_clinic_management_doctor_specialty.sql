CREATE TABLE doctor_specialty
(
    doctor_id    INT NOT NULL,
    specialty_id INT NOT NULL,
    PRIMARY KEY (doctor_id, specialty_id),
    CONSTRAINT fk_doctor_specialty_doctor
        FOREIGN KEY (doctor_id)
            REFERENCES doctor (doctor_id),
    CONSTRAINT fk_doctor_specialty_specialty
        FOREIGN KEY (specialty_id)
            REFERENCES specialty (specialty_id)
);

insert into doctor_specialty (doctor_id, specialty_id)
values (1, 1);