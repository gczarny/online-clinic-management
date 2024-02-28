INSERT INTO address (address_id, country, city, postal_code, street)
VALUES (1, 'Poland', 'Warsaw', '00-001', 'Marszałkowska 1'),
       (2, 'Poland', 'Krakow', '30-001', 'Floriańska 1'),
       (3, 'Poland', 'Gdańsk', '20-001', 'Testowa 10');

INSERT INTO clinic_user (user_id, user_name, email, phone, password, active, address_id)
VALUES (1, 'janusz_testowy', 'jantest@gmail.com', '+48000111222',
        '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true, 1),
       (2, 'marcelina_marcowa', 'marmar@mail.com', '+48000111333',
        '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true, 2),
       (3, 'anna_annowata', 'annanno@wp.pl', '+48000111444',
        '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true, 3);

insert into role(role_id, role_name)
VALUES (1,'ADMIN'),
       (2, 'DOCTOR'),
       (3, 'PATIENT');

INSERT INTO user_role (user_id, role_id)
VALUES (1, 3),
       (2, 2),
       (3, 3);

INSERT INTO doctor (doctor_id, first_name, last_name, user_id)
VALUES (1, 'Marcelina', 'Marcowa', 2);

INSERT INTO patient (patient_id, first_name, last_name, pesel, user_id)
VALUES (1, 'Janusz', 'Testowy', '21345678911', 1);

INSERT INTO patient (patient_id, first_name, last_name, pesel, user_id)
VALUES (2, 'Anna', 'Annowata', '12345678911', 3);

INSERT INTO doctor_availability (available_from, available_until, date_range_start, date_range_end, status, doctor_id)
VALUES ('08:00', '12:00', '2023-01-01', '2023-01-31', 'available', 1);

INSERT INTO appointment (appointment_id, appointment_date, status, reason, patient_id, doctor_id)
VALUES (1, '2023-01-15 09:00:00+00', 'scheduled', 'Routine checkup', 1, 1),
       (2, '2023-01-16 09:00:00+00', 'scheduled', 'Sick', 2, 1);

INSERT INTO medical_note (content, appointment_id)
VALUES ('', 1),
       ('PatientEntity has a cold, prescribed medication.', 2);


INSERT INTO patient_history (date, notes, patient_id)
VALUES ('2023-01-15', 'No issues found.', 1),
       ('2023-01-16', 'Cold, medication taken', 2);