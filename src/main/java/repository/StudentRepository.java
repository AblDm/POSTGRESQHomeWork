package repository;

import model.Faculty;
import model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findById(String faculty);


    Optional<Student> findById(Long id);
    Collection<Student> findAllByName (String name);

}
