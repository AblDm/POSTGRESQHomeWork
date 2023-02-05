package com.example.postgresqhomework.repository;

import com.example.postgresqhomework.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAge(int age);
    Collection<Student> findAllByNameContainsIgnoreCase (String part);
    Collection<Student> findStudentByAgeIsBetween(int age1, int age2);
    Collection<Student> findStudentsByFacultyId(Long id);

    Student getById (Long id);
    //- Возможность получить количество всех студентов в школе. Эндпоинт должен вернуть число.
    @Query(value = "SELECT COUNT(*) as count FROM student",nativeQuery = true)
    Integer getCount ();


    //- Возможность получить средний возраст студентов. Эндпоинт должен вернуть число.
    @Query(value = "SELECT AVG(age) as age FROM student",nativeQuery = true)
    Double getAverageAgeStuds ();
    //- Возможность получать только пять последних студентов. Последние студенты считаются теми, у кого идентификатор больше других.
    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5",nativeQuery = true)
    Collection<Student> listLastFiveStuds();

    List<Student> findAll();
}
