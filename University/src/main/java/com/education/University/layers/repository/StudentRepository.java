package com.education.University.layers.repository;
import com.education.University.layers.domain.student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
// this class is responsible for communication between data storage
public class StudentRepository {
    private JdbcTemplate jdbcTemplate;
    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
       // System.out.println("Jdbc template value is" + jdbcTemplate);
    }
    public student getstudent(Long id){
        RowMapper<student> rowMapper = new BeanPropertyRowMapper<>(student.class);
        try{
            student stu = jdbcTemplate.queryForObject("Select * From \"students\" Where id=?",rowMapper,id);
            return stu;
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    
}
