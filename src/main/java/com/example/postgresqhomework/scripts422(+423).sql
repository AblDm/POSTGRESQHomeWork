
--у каждого человека есть машина.
-- Причем несколько человек могут пользоваться одной машиной.
-- У каждого человека есть имя, возраст и признак того, что у него есть права (или их нет).
-- У каждой машины есть марка, модель и стоимость.
-- Также не забудьте добавить таблицам первичные ключи и связать их.
CREATE TABLE cars
(
    id    UUID
        PRIMARY KEY,
    brand TEXT NOT NULL,
    model TEXT NOT NULL,
    price BIGINT NOT NULL
);
CREATE TABLE peoples
(
    name TEXT NOT NULL
        PRIMARY KEY,
    age INTEGER NOT NULL,
    have_license BOOLEAN NOT NULL,
    car_id UUID
        REFERENCES cars (id)
);
--Составить первый JOIN-запрос, чтобы получить информацию обо всех студентах
-- (достаточно получить только имя и возраст студента) школы Хогвартс вместе с названиями факультетов.
SELECT student.name, student.age, faculty.name
FROM student
         LEFT JOIN faculty ON student.faculty_id = faculty.id;

--Составить второй JOIN-запрос, чтобы получить только тех студентов, у которых есть аватарки.
SELECT student.name, student.age
FROM student
         INNER JOIN avatar a ON student.id = a.student_id