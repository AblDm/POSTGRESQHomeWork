package service;

import model.Student;
import org.springframework.stereotype.Service;
import repository.StudentRepository;

import java.util.Collection;
import java.util.Optional;

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
        return studentRepository.findById(id).get();
    }


    public Student editStudent(long id, Student student) {
        if (!studentRepository.findAll().contains(id)) {
            return null;
        }
        studentRepository.deleteById(id);
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

}
