package repository;

import model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Faculty findByColor(String color);
    Collection<Faculty> findByName(String name);
    Collection<Faculty> findAllByColorContains (String part);

}