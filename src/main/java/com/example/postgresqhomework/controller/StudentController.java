package com.example.postgresqhomework.controller;
import com.example.postgresqhomework.model.Faculty;
import com.example.postgresqhomework.model.Student;
import com.example.postgresqhomework.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping //POST http://localhost:8080/student
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("{id}") //GET http://localhost:8080/student
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("ageFilter")//GET http://localhost:8080/student/ageFilter
    public ResponseEntity<Collection<Student>> findStudentsByAgeBetween (@RequestParam Integer ageMin,
                                                                         @RequestParam Integer ageMax) {
       if (ageMax!=null && ageMin!=null && ageMin<ageMax){
        return ResponseEntity.ok(studentService.findByAgeBetween(ageMin,ageMax));}

        return ResponseEntity.ok(Collections.emptyList());
    }
    @GetMapping(path = "{id}/faculty")//GET http://localhost:8080/student/{id}/faculty
    public ResponseEntity<Faculty> getFacultyById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findFaculty(id));
    }
    @PutMapping("{id}")//GET http://localhost:8080/student/{id}
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student foundStudent = studentService.findStudent(student.getId());
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(studentService.editStudent(student));
    }

    @DeleteMapping("{id}")//GET http://localhost:8080/student/{id}
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }


}
