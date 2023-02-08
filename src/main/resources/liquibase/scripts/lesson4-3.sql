--liquibase formatted sql

--changeset Dmabl
CREATE INDEX student_name_index ON student (name);

--changeset Dmabl
CREATE INDEX faculty_name_and_color ON faculty (name,color);