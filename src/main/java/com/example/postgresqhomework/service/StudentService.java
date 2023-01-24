package com.example.postgresqhomework.service;

import com.example.postgresqhomework.model.Faculty;
import com.example.postgresqhomework.model.Student;
import com.example.postgresqhomework.repository.FacultyRepository;
import org.springframework.stereotype.Service;
import com.example.postgresqhomework.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService  {


    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;


    public StudentService(StudentRepository studentRepository,
                          FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;

        this.facultyRepository = facultyRepository;
    }

    public Student createStudent(Student student) {
       return studentRepository.save(student);
    }


    public Student findStudent(long id) {
        return studentRepository.findById(id).orElse(null);
    }


    public Student editStudent(long id, Student student) {
        return studentRepository.save(student);
    }


    public void deleteStudent(long id) {
       studentRepository.deleteById(id);
    }

    public Collection<Student> findByAge (int age){
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findAllByNameContains (String part){
        return studentRepository.findAllByNameContainsIgnoreCase(part);
    }
    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public Collection<Student> findByAgeBetween (int age1, int age2){
        return studentRepository.findStudentByAgeIsBetween (age1, age2);
    }

    public Collection<Student> findByColorOrName(String part) {
        return studentRepository.findAllByNameContainsIgnoreCase(part);
    }

    public Faculty findFaculty(Long id) {
        return facultyRepository.findFacultyByStudentsId(id);
    }
}
