----------------------------------------------
-- Athletes
----------------------------------------------

DROP TABLE IF EXISTS athlete;

CREATE TABLE athlete (
    id_athlete   SERIAL PRIMARY KEY,
    first_name   VARCHAR(255),
    last_name    VARCHAR(255),
    age          INTEGER,
    gender       VARCHAR(255)
);

INSERT INTO athlete (id_athlete, first_name, last_name, age, gender) VALUES
(99990, 'Alain', 'Provist', 27, 'Male'),
(99991, 'Amour', 'Hire De Rire', 32, 'Male'),
(99992, 'Aude', 'Hébussavabien', 23, 'Female'),
(99993, 'Bill', 'Hattérahl', 31, 'Male'),
(99994, 'Armelle', 'Couvert', 25, 'Female'),
(99995, 'Beth', 'Rave', 28, 'Female'),
(99996, 'Daisy', 'Dyossi', 35, 'Female'),
(99997, 'Douglas', 'Ochocola', 22, 'Male'),
(99998, 'Helmut', 'Tardelpic', 41, 'Male'),
(99999, 'Ines', 'Pairet', 29, 'Female');


----------------------------------------------
-- Competitions
----------------------------------------------

DROP TABLE IF EXISTS competition;

CREATE TABLE competition (
    id_competition SERIAL PRIMARY KEY,
    designation    VARCHAR(255),
    city           VARCHAR(255),
    event_date     DATE,
    distance       FLOAT,
    max_athletes   INTEGER
);

INSERT INTO competition (id_competition, designation, city, event_date, distance, max_athletes) VALUES
(88880, 'Semi-marathon de Liffré', 'Liffré', '2024-04-06', 21.1, 500),
(88881, '100km de Crevin', 'Crevin', '2024-04-05', 100, 100),
(88882, 'Rennes Urban Trail', 'Rennes', '2024-04-27', 24, 750),
(88883, 'Tour de l''île d''Arz', 'Île d''Arz', '2024-05-17', 10, 300),
(88884, 'Trail de l''Enfer', 'Plouguiel', '2024-07-06', 18, 200);

----------------------------------------------
-- Registration
----------------------------------------------

CREATE TABLE registration (
    id_registration       SERIAL PRIMARY KEY,
    id_athlete            BIGINT REFERENCES athlete(id_athlete),
    id_competition        BIGINT REFERENCES competition(id_competition),
    registration_date     DATE
);

INSERT INTO registration (id_registration, id_athlete, id_competition, registration_date) VALUES
(77770, 99990, 88880, '2024-03-01'),
(77771, 99991, 88881, '2024-03-02'),
(77772, 99992, 88882, '2024-03-03'),
(77773, 99993, 88883, '2024-03-04'),
(77774, 99994, 88884, '2024-03-05'),
(77775, 99995, 88880, '2024-03-06'),
(77776, 99996, 88881, '2024-03-07'),
(77777, 99997, 88882, '2024-03-08'),
(77778, 99998, 88883, '2024-03-09'),
(77779, 99999, 88884, '2024-03-10'),
(77780, 99990, 88881, '2024-03-11'),
(77781, 99991, 88882, '2024-03-12'),
(77782, 99992, 88883, '2024-03-13'),
(77783, 99993, 88884, '2024-03-14'),
(77784, 99994, 88880, '2024-03-15'),
(77785, 99995, 88882, '2024-03-16'),
(77786, 99996, 88883, '2024-03-17'),
(77787, 99997, 88884, '2024-03-18'),
(77788, 99998, 88880, '2024-03-19'),
(77789, 99999, 88881, '2024-03-20'),
(77790, 99990, 88882, '2024-03-21'),
(77791, 99991, 88883, '2024-03-22'),
(77792, 99992, 88884, '2024-03-23'),
(77793, 99993, 88880, '2024-03-24'),
(77794, 99994, 88881, '2024-03-25'),
(77795, 99995, 88883, '2024-03-26'),
(77796, 99996, 88884, '2024-03-27'),
(77797, 99997, 88880, '2024-03-28'),
(77798, 99998, 88881, '2024-03-29'),
(77799, 99999, 88882, '2024-03-30');