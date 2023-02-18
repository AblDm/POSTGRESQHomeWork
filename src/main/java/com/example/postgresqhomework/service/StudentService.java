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
import java.util.stream.Collectors;

@Service
public class StudentService {


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

    public Collection<Student> findByAge(int age) {
        logger.info("Was invoked method \"find student by age\"");
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findAllByNameContains(String part) {
        logger.info("Was invoked method \"find student by part name\"");
        return studentRepository.findAllByNameContainsIgnoreCase(part);
    }

    public Student editStudent(Student student) {
        logger.info("Was invoked method \"edit student\"");
        return studentRepository.save(student);
    }

    public Collection<Student> findByAgeBetween(int age1, int age2) {
        logger.info("Was invoked method \"find student in age interval\"");
        return studentRepository.findStudentByAgeIsBetween(age1, age2);
    }

    public Faculty findFaculty(Long id) {
        logger.info("Was invoked method \"find student faculty id\"");
        return facultyRepository.findFacultyByStudentsId(id);
    }

    public Integer getCount() {
        logger.info("Was invoked method \"count students\"");
        return studentRepository.getCount();
    }

    public Double getAverageAgeStuds() {
        logger.info("Was invoked method \"count average Age\"");
        return studentRepository.getAverageAgeStuds();
    }

    ;

    public Collection<Student> lastFiveStuds() {
        logger.info("Was invoked method \"list last 5 students of academy\"");
        return studentRepository.listLastFiveStuds();
    }

    public Page<Student> getStudents(Pageable pageable) {
        logger.info("Was invoked method \"pages view students of academy\"");
        return studentRepository.findAll(pageable);
    }

    //Создан эндпоинт, который возвращает отсортированные в алфавитном порядке имена всех студентов в верхнем регистре, чье имя начинается на букву А.
    public List<String> getStudsWhosNamesBeginsWithA() {
        return studentRepository.findAll()
                .stream()
                .map(Student::getName)
                .sorted()
                .filter(s -> s.charAt(0) == 'А' || s.charAt(0) == 'a')
                .map(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase())
                .collect(Collectors.toList());
    }

    // Создан эндпоинт, который возвращает средний возраст всех студентов.
    public Double getAvgAge() {
        return studentRepository.findAll()
                .stream()
                .mapToInt(Student::getAge)
                .average()
                .orElseThrow();
    }


    //Cписок всех студентов и вывести их имена в консоль используя команду System.out.println(). При этом первые два имени в основном потоке, второе и третье в параллельном потоке.  пятое и шестое во втором параллельном потоке. В итоге в консоле должен появиться список из шести имен в порядке, отличном от порядка в коллекции.
    public void getStudentsPrint() {

        List<String> studs = studentRepository.findAll().stream()
                .map(Student::getName)
                .toList();

//первые два имени в основном потоке,
        System.out.println(studs.get(0));
        System.out.println(studs.get(1));

//второе и третье в параллельном потоке
        new Thread(() -> {
            System.out.println(studs.get(1));
            System.out.println(studs.get(2));
        }).start();

//пятое и шестое во втором параллельном потоке
        new Thread(() -> {
            System.out.println(studs.get(3));
            System.out.println(studs.get(4));
        }).start();
    }

    public void getStudentsSynchronisedPrint() {



//первые два имени в основном потоке,
        synchronisedPrint(0);
        synchronisedPrint(1);

//второе и третье в параллельном потоке
        new Thread(() -> {
            synchronisedPrint(1);
            synchronisedPrint(2);
        }).start();

//пятое и шестое во втором параллельном потоке
        new Thread(() -> {
            synchronisedPrint(3);
            synchronisedPrint(4);
        }).start();
    }

//вывод имени в консоль вынести в отдельный синхронизированный метод.


    public void synchronisedPrint(int i) {
       synchronized (StudentService.class) {
           List<String> studs = studentRepository.findAll().stream()
                   .map(Student::getName)
                   .toList();
           System.out.println(studs.get(i));
       }
    }
 }