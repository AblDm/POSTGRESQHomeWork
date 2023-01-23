--1. Получить всех студентов, возраст которых находится между 10 и 20 (можно подставить любые числа, главное, чтобы нижняя граница была меньше верхней).
select * from student where age >10 and age <20;
--2. Получить всех студентов, но отобразить только список их имен.
select name from student;
--3. Получить всех студентов, у которых в имени присутствует буква «О» (или любая другая).
select * from student where name like '%о%';
--4. Получить всех студентов, у которых возраст меньше идентификатора.
select * from student where age <student.id;
--5. Получить всех студентов упорядоченных по возрасту.
select * from student   order by age;
