package repository;

import model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
//    @Transactional
//    @Modifying
//    @Query("""
//            update Faculty f set f.id = :id1, f.name = :name2, f.color = :color3
//            where f.id = :id4 and f.name = :name5 and f.color = :color6""")
//    Faculty updateById(@Param("id") Long id, @Param("name") String name, @Param("color") String color);
    List<Faculty> findByColor(String faculty);
}