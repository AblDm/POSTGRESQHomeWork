package com.example.postgresqhomework.repository;


import com.example.postgresqhomework.model.Avatar;
import com.example.postgresqhomework.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findById(Long aLong);
}
