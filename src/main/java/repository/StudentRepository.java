package repository;

import model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findById(String faculty);

//    void updateId(long id, Student student);
//
//    @Transactional
//    @Modifying
//    @Query("""
//            update Student s set s.id = :id1, s.name = :name2, s.age = :age3
//            where s.id = :id4 and s.name = :name5 and s.age = :age6""")
//    Student updateById(@Param("id") Long id, @Param("name") String name, @Param("age") int age);


}
