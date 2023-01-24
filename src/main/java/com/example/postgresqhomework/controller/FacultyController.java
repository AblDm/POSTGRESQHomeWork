package com.example.postgresqhomework.controller;

import com.example.postgresqhomework.model.Faculty;
import com.example.postgresqhomework.model.Student;
import org.springframework.http.HttpStatus;
import com.example.postgresqhomework.service.FacultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }


    @PostMapping("/")
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id){
        Faculty faculty = facultyService.findFacultyById(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }
    @GetMapping("{id}/students")
    public ResponseEntity <Collection<Student>> getStudentsOnFaculty(@PathVariable Long id){
        return ResponseEntity.ok(facultyService.findStudentsByFacultyId(id));
    }
    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }
    @DeleteMapping("{id}")
    public ResponseEntity deleteFaculty (@PathVariable Long id){
         facultyService.deleteFacultyById(id);
         return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> findFaculties(@RequestParam(required = false) String color,
                                                             @RequestParam(required = false) String name) {
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(Collections.singleton(facultyService.findByColor(color)));
        }
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(facultyService.findByName(name));
        }

        return ResponseEntity.ok(Collections.emptyList());
    }
}
