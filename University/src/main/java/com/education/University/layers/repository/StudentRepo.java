package com.education.University.layers.repository;

import com.education.University.layers.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {

    List<Student> findAllByNameStartsWith(String start);
    @Query(value = "select * from students where name = ?", nativeQuery = true)
    List<Student> findMyStudents(String name);

}
