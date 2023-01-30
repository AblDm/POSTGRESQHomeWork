package com.example.postgresqhomework.service;

import com.example.postgresqhomework.model.Faculty;
import com.example.postgresqhomework.model.Student;
import com.example.postgresqhomework.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.postgresqhomework.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public FacultyService(FacultyRepository facultyRepository,
                          StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }


    public Faculty createFaculty (Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFacultyById(Long id) {
       return facultyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("NotFoundEntity")) ;
    }
    public void deleteFacultyById(Long id) {
        facultyRepository.deleteById(id);
    }

    public Faculty editFaculty (Long id, Faculty faculty) {
       return facultyRepository.save(faculty);
    }

    public Collection<Faculty> findByName(String name){
        return facultyRepository.findByNameIgnoreCase(name);
    }

    Collection<Faculty> findAllByColorContains (String part){
        return facultyRepository.findAllByColorContainsIgnoreCase(part);
    }

    public Faculty findByColor(String color) {
        return facultyRepository.findByColorIgnoreCase(color);
        }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Collection<Student> findStudentsByFacultyId(Long id){
        return studentRepository.findStudentsByFacultyId(id);
    }
}

