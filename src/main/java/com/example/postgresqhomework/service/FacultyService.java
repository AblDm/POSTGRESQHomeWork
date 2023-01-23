package com.example.postgresqhomework.service;

import com.example.postgresqhomework.model.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.postgresqhomework.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty createFaculty (Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFacultyById(Long id) {
        return facultyRepository.findById(id).orElse(null);
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
}

