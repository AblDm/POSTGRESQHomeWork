package com.example.postgresqhomework.controller;
import com.example.postgresqhomework.model.Avatar;
import com.example.postgresqhomework.model.Faculty;
import com.example.postgresqhomework.model.Student;
import com.example.postgresqhomework.service.AvatarServiceI;
import com.example.postgresqhomework.service.StudentService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final AvatarServiceI avatarServiceI;

    public StudentController(StudentService studentService, AvatarServiceI avatarServiceI) {
        this.studentService = studentService;
        this.avatarServiceI = avatarServiceI;
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

    @PostMapping(value = "/{id}/avatars",consumes = MediaType.MULTIPART_FORM_DATA_VALUE) //POST http://localhost:8080/student/23/avatars
    public ResponseEntity<String> uploadAvatar (@PathVariable Long id, @RequestParam MultipartFile file) throws IOException {
        if (file.getSize()> 1024 * 300){
            return ResponseEntity.badRequest().body("File is too big");
        }
        avatarServiceI.uploadAvatar(id, file);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/count")  //GET http://localhost:8080/student/count
    public ResponseEntity<Integer> studentsCount() {
        return ResponseEntity.ok(studentService.getCount());
    }

    @GetMapping(path = "/avg-age")  //GET http://localhost:8080/student/avg-age
    public ResponseEntity<Double> studentsAvgAge() {
        return ResponseEntity.ok(studentService.getAverageAgeStuds());
    }

    @GetMapping(path = "/last-five")  //GET http://localhost:8080/student/last-five
    public ResponseEntity<Collection<Student>> lastFiveStuds(){
        return ResponseEntity.ok(studentService.lastFiveStuds());
    }


}
