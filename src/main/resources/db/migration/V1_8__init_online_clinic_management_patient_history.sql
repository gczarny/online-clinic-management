create table patient_history
(
    patient_history_id SERIAL      NOT NULL,
    date       DATE NOT NULL,
    notes TEXT NOT NULL,
    patient_id INTEGER NOT NULL,
    primary key (patient_history_id),
    CONSTRAINT fk_history_patient
        FOREIGN KEY (patient_id)
            REFERENCES patient (patient_id)
);