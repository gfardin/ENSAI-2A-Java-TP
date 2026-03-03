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
