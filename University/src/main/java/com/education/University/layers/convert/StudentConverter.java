package com.education.University.layers.convert;

import com.education.University.layers.dto.StudentDto;
import com.education.University.layers.domain.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter {
    public StudentDto fromDomain(Student student) {
        return new StudentDto(student.getName(), student.isGraduated(),student.getEmail(), student.getPhoneNum());

    }

    public Student fromDto(StudentDto studentDto) {
        return new Student(studentDto.getName(),studentDto.isGraduated(),studentDto.getEmail(), studentDto.getPhoneNum());
    }
}
