package com.education.University.layers.service;

import com.education.University.layers.dto.StudentDto;
import com.education.University.layers.convert.StudentConverter;
import com.education.University.layers.domain.Student;
import com.education.University.layers.exceptions.DataNotFoundException;
import com.education.University.layers.exceptions.SemanticException;
import com.education.University.layers.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private StudentRepository Repository;
    private StudentConverter studentConverter;
    public StudentService(StudentRepository Repository, StudentConverter studentConverter) {
        this.Repository = Repository;
        this.studentConverter = studentConverter;
    }

    public List<StudentDto> getStudents() {
        return Repository.getStudents().
                stream()
                .map(student -> studentConverter.fromDomain(student)).toList();
    }

    public StudentDto createStudent(StudentDto studentDto){
        validate(studentDto);

        Student createdStudent=Repository.createStudent(studentConverter.fromDto(studentDto));
        return studentConverter.fromDomain(createdStudent);
    }
    public StudentDto updateStudent(Long id,StudentDto studentDto ){
        checkExisting(id);
        validate(studentDto);

      return Repository.updateStudent(studentConverter.fromDto(studentDto),id)
                .map(s -> studentConverter.fromDomain(s)).get();
    }
    public void deleteStudent(Long id) {
       checkExisting(id);
       Repository.deleteStudent(id);
    }
    private void validate(StudentDto studentDto) {
        if(studentDto.getPhoneNum()<10){
            throw new SemanticException("Phone number should be larger than 10");
        }
    }
    private void checkExisting(Long id) {
//        Student student=Repository.getStudent(id);
//        if(student != null){
//            System.out.println("Delete student with id:" + id);
//        }
//        else{
//            throw new DataNotFoundException("Book with id" + id + " is not found");
//        }
        Repository
                .getStudent(id)
                .map(s ->{
                    System.out.println("checkingExisting student with id:" + id);
                    return s;
                })
                .orElseThrow(()->new DataNotFoundException("Book with id" + id + " is not found"));
    }

    public StudentDto getStudent(Long id) {
        return Repository.getStudent(id).map(s -> studentConverter.fromDomain(s)).
                orElseThrow(()->new DataNotFoundException("Book with id" + id + " is not found"));
    }
}
