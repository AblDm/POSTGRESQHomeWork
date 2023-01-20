package controller;

import model.Faculty;
import service.FacultyService;
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

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id){
        Faculty faculty = facultyService.findFacultyById(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping("/")
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping
    public  ResponseEntity <Faculty> editFaculty (@RequestBody Long id, Faculty faculty){
        facultyService.deleteFacultyById(id);
        facultyService.createFaculty(faculty);
        return ResponseEntity.ok(faculty);
    }

    @DeleteMapping("{id}")
    public void deleteFaculty (@PathVariable Long id){
         facultyService.deleteFacultyById(id);
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> findFaculties(@RequestParam(required = false) String color) {
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(Collections.singleton(facultyService.findByColor(color)));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }
}
