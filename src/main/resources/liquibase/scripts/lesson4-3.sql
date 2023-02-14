--liquibase formatted sql

--changeset Dmabl:1
CREATE INDEX student_name_index ON student (name);

--changeset Dmabl:2
CREATE INDEX faculty_name_and_color ON faculty (name,color);