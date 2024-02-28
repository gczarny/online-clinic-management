create table appointment (
    appointment_id SERIAL NOT NULL,
    appointment_date TIMESTAMP WITH TIME ZONE NOT NULL,
    status VARCHAR(50) NOT NULL,
    reason TEXT NOT NULL,
    patient_id int NOT NULL,
    doctor_id int NOT NULL,
    primary key (appointment_id),
    constraint fk_appointment_patient
        foreign key (patient_id)
            references patient (patient_id),
    constraint fk_appointment_doctor
        foreign key (doctor_id)
            references doctor (doctor_id)
);