package com.education.University.layers.repository;

import com.education.University.layers.domain.Student;
import com.education.University.layers.domain.Uni;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UniRepository {
    private static JdbcTemplate jdbcTemplate;

    private RowMapper<Uni> rowMapper;

    public UniRepository(JdbcTemplate jdbcTemplate,RowMapper<Uni> rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }


}
