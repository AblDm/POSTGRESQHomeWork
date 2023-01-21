package com.example.postgresqhomework.service;

import com.example.postgresqhomework.model.Student;
import org.springframework.stereotype.Service;
import com.example.postgresqhomework.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService  {


    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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
        return studentRepository.findAllByNameContains(part);
    }
    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }
}
