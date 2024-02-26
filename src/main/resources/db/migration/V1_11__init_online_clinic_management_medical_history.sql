create table medical_history (
    medical_history_id SERIAL NOT NULL,
    date DATE NOT NULL,
    notes TEXT NOT NULL,
    patient_id INT NOT NULL,
    primary key (medical_history_id),
    constraint fk_medical_history_patient
        foreign key (patient_id)
            references patient (patient_id)
);