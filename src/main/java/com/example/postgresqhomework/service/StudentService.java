package com.example.postgresqhomework.service;

import com.example.postgresqhomework.model.Faculty;
import com.example.postgresqhomework.model.Student;
import com.example.postgresqhomework.repository.FacultyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.postgresqhomework.repository.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService  {


    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;


    public StudentService(StudentRepository studentRepository,
                          FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;

        this.facultyRepository = facultyRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public Student createStudent(Student student) {
        logger.info("Was invoked method \"create student\"");
        return studentRepository.save(student);
    }


    public Student findStudent(long id) {
        logger.info("Was invoked method \"create student\"");
        return studentRepository.findById(id).orElse(null);
    }





    public void deleteStudent(long id) {
        logger.info("Was invoked method \"delete student\"");
        studentRepository.deleteById(id);
    }

    public Collection<Student> findByAge (int age){
        logger.info("Was invoked method \"find student by age\"");
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findAllByNameContains (String part){
        logger.info("Was invoked method \"find student by part name\"");
        return studentRepository.findAllByNameContainsIgnoreCase(part);
    }
    public Student editStudent(Student student) {
        logger.info("Was invoked method \"edit student\"");
        return studentRepository.save(student);
    }

    public Collection<Student> findByAgeBetween (int age1, int age2){
        logger.info("Was invoked method \"find student in age interval\"");
        return studentRepository.findStudentByAgeIsBetween (age1, age2);
    }

    public Faculty findFaculty(Long id) {
        logger.info("Was invoked method \"find student faculty id\"");
        return facultyRepository.findFacultyByStudentsId(id);
    }

    public Integer getCount() {
        logger.info("Was invoked method \"count students\"");
        return studentRepository.getCount();
    }

    public Double getAverageAgeStuds (){
        logger.info("Was invoked method \"count average Age\"");
        return studentRepository.getAverageAgeStuds();
    };

    public Collection<Student> lastFiveStuds(){
        logger.info("Was invoked method \"list last 5 students of academy\"");
        return studentRepository.listLastFiveStuds();
    }

    public Page<Student> getStudents(Pageable pageable) {
        logger.info("Was invoked method \"pages view students of academy\"");
        return studentRepository.findAll(pageable);
    }
}
