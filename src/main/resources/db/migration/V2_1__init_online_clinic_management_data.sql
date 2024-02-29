INSERT INTO address (address_id, country, city, postal_code, street)
VALUES (1, 'Poland', 'Warsaw', '00-001', 'Marszałkowska 1'),
       (2, 'Poland', 'Krakow', '30-001', 'Floriańska 1'),
       (3, 'Poland', 'Gdańsk', '20-001', 'Testowa 10'),
       (4, 'Poland', 'Katowice', '20-002', 'Testowa 30'),
       (5, 'Poland', 'Wrocław', '20-003', 'Testowa 40'),
       (6, 'Poland', 'Poznań', '20-004', 'Testowa 50');


INSERT INTO clinic_user (user_id, user_name, email, phone, password, active, address_id)
VALUES (1, 'janusz_testowy', 'jantest@gmail.com', '+48000111222',
        '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true, 1),
       (2, 'marcelina_marcowa', 'marmar@mail.com', '+48000111333',
        '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true, 2),
       (3, 'anna_annowata', 'annanno@wp.pl', '+48000111444',
        '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true, 3),
       (4, 'jan_kowalski', 'jankow@cos.tam', '+42220111444',
        '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true, 4),
       (5, 'zbyszek_wojownik', 'zbychu@mail.pl', '+42220112444',
        '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true, 5),
       (6, 'lekarz_lekarski', 'leklek@test.pl', '+42220111234',
        '$2a$12$TwQsp1IusXTDl7LwZqL0qeu49Ypr6vRdEzRq2vAsgb.zvOtrnzm5G', true, 6);

insert into role(role_id, role_name)
VALUES (1, 'ADMIN'),
       (2, 'DOCTOR'),
       (3, 'PATIENT');

INSERT INTO user_role (user_id, role_id)
VALUES (1, 3),
       (2, 2),
       (3, 3),
       (4, 3),
       (5, 2),
       (6, 2);

INSERT INTO doctor (doctor_id, first_name, last_name, user_id)
VALUES (1, 'Marcelina', 'Marcowa', 2),
       (2, 'Lekarz', 'Lekarski', 5),
       (3, 'Zbyszek', 'Wojownik', 6);


INSERT INTO patient (patient_id, first_name, last_name, pesel, user_id)
VALUES (1, 'Janusz', 'Testowy', '21345678911', 1),
       (2, 'Marcelina', 'Marcowa', '12345678911', 2),
       (3, 'Anna', 'Annowata', '12345678211', 3),
       (4, 'Jan', 'Kowalski', '12345378911', 4);

INSERT INTO doctor_availability (available_from, available_until, date_range_start, date_range_end, status, doctor_id)
VALUES ('08:00', '12:00', '2023-03-01', '2023-04-27', 'AVAILABLE', 1),
       ('06:00', '14:00', '2023-03-15', '2023-04-15', 'AVAILABLE', 2),
       ('10:00', '17:00', '2023-02-27', '2023-03-29', 'AVAILABLE', 3);

INSERT INTO appointment (appointment_id, appointment_date, status, reason, patient_id, doctor_id)
VALUES (1, '2023-03-15 09:00:00+00', 'SCHEDULED', 'Routine checkup', 1, 1),
       (2, '2023-03-16 09:00:00+00', 'SCHEDULED', 'Sick', 2, 1),
       (3, '2023-03-22 09:00:00+00', 'CANCELLED', 'Routine checkup', 3, 2),
       (4, '2023-03-18 09:00:00+00', 'DONE', 'Routine checkup', 4, 3);

INSERT INTO medical_note (content, appointment_id)
VALUES ('', 1),
       ('', 2),
       ('Caneled by caller', 3),
       ('PatientEntity has a cold, prescribed medication.', 4);


/*INSERT INTO patient_history (date, notes, patient_id)
VALUES ('2023-03-16', 'Cold, medication taken', 4);*/

