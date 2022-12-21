package com.education.University.layers.repository;
import com.education.University.layers.domain.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;


@Repository
// this class is responsible for communication between data storage
public class StudentRepository {
    private static JdbcTemplate jdbcTemplate;
    private RowMapper<Student> rowMapper;

    public StudentRepository(JdbcTemplate jdbcTemplate, RowMapper<Student> StudentRowMapper1) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = StudentRowMapper1;
    }
    public Student getStudent(Long id){
            Student student = jdbcTemplate.queryForObject("Select * From \"Students\" Where id=?",rowMapper,id);
            return student;
    }
    public Student createStudent(Student student){
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO public.students(\n" +
                                "\tname, graduated, email, phone_number)\n\tVALUES (?, ?, ?, ?)",new String[]{"id"});
                    preparedStatement.setString(1, student.getName());
                    preparedStatement.setBoolean(2, student.isGraduated());
                    preparedStatement.setString(3, student.getEmail());
                    preparedStatement.setInt(4, student.getPhoneNum());
                    return preparedStatement;

                },
                   keyHolder);

        long generatedKey = keyHolder.getKey().longValue();
        return getStudent(generatedKey);
    }
    public Student updateStudent(Student student,Long id){
        jdbcTemplate.update(con ->  {
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE public.students\n" +
                        "\tSET name=? ,graduated=?, email=?, phone_number=?\n" +
                        "\tWHERE  id =?");
                preparedStatement.setString(1, student.getName());
                preparedStatement.setBoolean(2, student.isGraduated());
                preparedStatement.setString(3, student.getEmail());
                preparedStatement.setInt(4, student.getPhoneNum());
                preparedStatement.setLong(5, student.getId());
                return preparedStatement;
            });

        return getStudent(id) ;
    }
    public void deleteStudent(Long id) {

        jdbcTemplate.update("DELETE FROM public.\"Students\" WHERE id=?;",id);
    }

}
