-- Возраст студента не может быть меньше 16 лет. (1 - не может не быть + не меньше 16)
ALTER TABLE student
    ALTER COLUMN age SET NOT NULL,
    ADD CONSTRAINT age_constraint CHECK (age >= 16),
    -- При создании студента без возраста ему автоматически должно присваиваться 20 лет.
    ALTER COLUMN age SET DEFAULT 20,
    -- Имена студентов должны быть уникальными и не равны нулю.
    ADD CONSTRAINT name_constraint UNIQUE(name);

-- Пара “значение названия” - “цвет факультета” должна быть уникальной.
ALTER TABLE faculty
    ADD CONSTRAINT name_color_unique UNIQUE (name, color);