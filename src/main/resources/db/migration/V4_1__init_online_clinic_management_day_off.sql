CREATE TABLE day_off
(
    day_off_id SERIAL PRIMARY KEY,
    doctor_id  INT  NOT NULL,
    date_off   DATE NOT NULL,
    CONSTRAINT fk_day_off_doctor
        FOREIGN KEY (doctor_id)
            REFERENCES doctor (doctor_id) ON DELETE CASCADE
);

INSERT INTO day_off (doctor_id, date_off)
VALUES (1, '2024-03-05'),
       (2, '2024-03-17'),
       (3, '2024-03-27'),
       (2, '2024-03-04'),
       (2, '2024-03-28'),
       (3, '2024-03-03');
