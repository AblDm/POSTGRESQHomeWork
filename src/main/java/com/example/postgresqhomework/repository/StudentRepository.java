package com.example.postgresqhomework.repository;

import com.example.postgresqhomework.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {




    Collection<Student> findByAge(int age);
    Collection<Student> findAllByNameContains (String part);

}
