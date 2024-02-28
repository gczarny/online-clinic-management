CREATE TABLE specialty
(
    specialty_id SERIAL       NOT NULL,
    name         VARCHAR(100) NOT NULL,
    PRIMARY KEY (specialty_id)
);

INSERT INTO specialty (name)
VALUES ('Cardiology'),
         ('Dermatology'),
         ('Endocrinology'),
         ('Gastroenterology'),
         ('Hematology'),
         ('Infectious Disease'),
         ('Nephrology'),
         ('Neurology'),
         ('Oncology'),
         ('Pulmonology'),
         ('Rheumatology'),
         ('Urology');
