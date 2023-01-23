package com.example.postgresqhomework.repository;

import com.example.postgresqhomework.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Faculty findByColorIgnoreCase(String color);
    Collection<Faculty> findByNameIgnoreCase(String name);
    Collection<Faculty> findAllByColorContainsIgnoreCase (String part);


}