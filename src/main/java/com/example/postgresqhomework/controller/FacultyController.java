package com.example.postgresqhomework.controller;

import com.example.postgresqhomework.model.Faculty;
import com.example.postgresqhomework.model.Student;
import com.example.postgresqhomework.repository.FacultyRepository;
import org.springframework.http.HttpStatus;
import com.example.postgresqhomework.service.FacultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.IntStream;


@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;
    private final FacultyRepository facultyRepository;

    public FacultyController(FacultyService facultyService,
                             FacultyRepository facultyRepository) {
        this.facultyService = facultyService;
        this.facultyRepository = facultyRepository;
    }


    @PostMapping("/")
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id){
        return ResponseEntity.ok(facultyService.findFacultyById(id));
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
        }   return ResponseEntity.ok(foundFaculty);
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

    @GetMapping(path = "longest-name")  //GET http://localhost:8080/faculty/longest-name
    public ResponseEntity<String> mostLongestFacultyName(){
        return ResponseEntity.ok(facultyService.longerFacultyName());
    }


    @GetMapping(path = "/getValue")   //GET http://localhost:8080/getValue
    public ResponseEntity<StringBuffer> getValue() {
        long startTime = System.currentTimeMillis();

        int sum = IntStream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, Integer::sum);

        long endTime = System.currentTimeMillis();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Метод работал ").append(endTime - startTime).append(" ms.")
                .append("\nСумма: ").append(sum);

        return ResponseEntity.ok(stringBuffer);
    }
}
