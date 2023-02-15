package com.example.postgresqhomework.service;

import com.example.postgresqhomework.model.Faculty;
import com.example.postgresqhomework.model.Student;
import com.example.postgresqhomework.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.postgresqhomework.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;


@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public FacultyService(FacultyRepository facultyRepository,
                          StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(FacultyService.class);
    public Faculty createFaculty (Faculty faculty) {
        logger.info("Was invoked method \"create faculty\"");
        return facultyRepository.save(faculty);
    }

    public Faculty findFacultyById(Long id) {
        logger.info("Was invoked method \"find faculty by id\"");
       return facultyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("NotFoundEntity")) ;
    }
    public void deleteFacultyById(Long id) {
        logger.info("Was invoked method \"delete faculty by id\"");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findByName(String name){
        logger.info("Was invoked method \"edit faculty \"");
        return facultyRepository.findByNameIgnoreCase(name);
    }

    Collection<Faculty> findAllByColorContains (String part){
        logger.info("Was invoked method \"find faculty by colors contain\"");
        return facultyRepository.findAllByColorContainsIgnoreCase(part);
    }

    public Faculty findByColor(String color) {
        logger.info("Was invoked method \"find faculty by colors\"");
        return facultyRepository.findByColorIgnoreCase(color);
        }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Was invoked method \"edit faculty \"");
        return facultyRepository.save(faculty);
    }

    public Collection<Student> findStudentsByFacultyId(Long id){
        logger.info("Was invoked method \"find faculty by id\"");
        return studentRepository.findStudentsByFacultyId(id);
    }

    //эндпоинт, который возвращает самое длинное название факультета.
    public String longerFacultyName(){
        return facultyRepository.findAll()
                .stream()
                .max(Comparator.comparing(f -> f.getFacultyName().length()))
                .map(Faculty::getFacultyName)
                .orElseThrow();
    }

}

