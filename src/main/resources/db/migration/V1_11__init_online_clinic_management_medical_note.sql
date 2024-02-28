create table medical_note (
    medical_note_id SERIAL NOT NULL,
    content TEXT NOT NULL,
    appointment_id int NOT NULL,
    primary key (medical_note_id),
    constraint fk_medical_note_appointment
        foreign key (appointment_id)
            references appointment (appointment_id)
);