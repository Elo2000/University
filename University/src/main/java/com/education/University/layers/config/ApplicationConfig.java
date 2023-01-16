package com.education.University.layers.config;

import com.education.University.layers.domain.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

@Configuration
public class ApplicationConfig {
    @Bean
    public String stringTwo() {
        return "Another String";
    }
    @Bean
    public String myVeryCoolString() {
        return "Hello from Config !!!!";
    }

    @Bean("stringbean")
    public String thirdString() {
        return "Third !!!!";
    }
    @Bean
    public RowMapper<Student> studentRowMapper1() {
        return new BeanPropertyRowMapper<>(Student.class);
    }
//    @Bean
//    public RowMapper<Student> studentRowMapper2() {
//        return new BeanPropertyRowMapper<>(Student.class);
//    }




}
