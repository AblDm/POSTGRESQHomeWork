package repository;

import model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {




    Collection<Student> findByAge(int age);
    Collection<Student> findAllByNameContains (String part);

}
