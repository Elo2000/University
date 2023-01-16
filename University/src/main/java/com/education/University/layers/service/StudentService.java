package com.education.University.layers.service;

import com.education.University.layers.dto.StudentDto;
import com.education.University.layers.convert.StudentConverter;
import com.education.University.layers.domain.Student;
import com.education.University.layers.exceptions.DataNotFoundException;
import com.education.University.layers.exceptions.SemanticException;
import com.education.University.layers.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//this class is used for logic
@Service
public class StudentService {
    private StudentRepository repository;

    private StudentConverter studentConverter;
    public StudentService(StudentRepository repository, StudentConverter studentConverter) {
       // System.out.println("Constructor is called");
        this.repository = repository;
        this.studentConverter = studentConverter;
    }

    public List<StudentDto> getStudents() {
        return repository.getStudents().
                stream()
                .map(student -> studentConverter.fromDomain(student)).toList();
    }

    public StudentDto createStudent(StudentDto studentDto){
        validate(studentDto);

        Student createdStudent=repository.createStudent(studentConverter.fromDto(studentDto));
        return studentConverter.fromDomain(createdStudent);
    }
    public StudentDto updateStudent(Long id,StudentDto studentDto ){
        checkExisting(id);
        validate(studentDto);

      return repository.updateStudent(studentConverter.fromDto(studentDto),id)
                .map(s -> studentConverter.fromDomain(s)).get();
    }
    public void deleteStudent(Long id) {
       checkExisting(id);
       repository.deleteStudent(id);
    }
    private void validate(StudentDto studentDto) {
        if(studentDto.getPhoneNum()<10){
            throw new SemanticException("Phone number should be larger than 10");
        }
    }
    private void checkExisting(Long id) {

        repository
                .getStudent(id)
                .map(s ->{
                    System.out.println("checkingExisting student with id:" + id);
                    return s;
                })
                .orElseThrow(()->new DataNotFoundException("Student with id" + id + " is not found"));
    }

    public StudentDto getStudent(Long id) {
        Optional<Student> student = repository.getStudent(id);
        return student.map(s -> studentConverter.fromDomain(s)).
                orElseThrow(()->new DataNotFoundException("Student with id " +  id + " is not found"));
    }
}
