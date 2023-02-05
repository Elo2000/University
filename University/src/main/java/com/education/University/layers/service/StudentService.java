package com.education.University.layers.service;

import com.education.University.layers.dto.StudentDto;
import com.education.University.layers.convert.StudentConverter;
import com.education.University.layers.domain.Student;
import com.education.University.layers.exceptions.DataNotFoundException;
import com.education.University.layers.exceptions.SemanticException;
import com.education.University.layers.repository.StudentRepo;
import com.education.University.layers.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//this class is used for logic
@Service
public class StudentService {
    private StudentRepo studentRepo;
    //private StudentRepository repository;

    private StudentConverter studentConverter;
    public StudentService( StudentConverter studentConverter,StudentRepo studentRepo) {
       // System.out.println("Constructor is called");
      //  this.repository = repository;
        this.studentConverter = studentConverter;
        this.studentRepo=studentRepo;
    }

    public List<StudentDto> getStudents() {
        return studentRepo.findAll()
                .stream()
                .map(student -> studentConverter.fromDomain(student)).toList();
    }

    public StudentDto createStudent(StudentDto studentDto){
        validate(studentDto);

        Student createdStudent=studentRepo.save(studentConverter.fromDto(studentDto));
        return studentConverter.fromDomain(createdStudent);
    }
    public StudentDto updateStudent(Long id,StudentDto studentDto ){
       // checkExisting(id);
        validate(studentDto);

        return studentRepo.findById(id)
                      .map(student -> {
                          Student foundStudent = studentConverter.fromDto(studentDto);
                          foundStudent.setId(student.getId());
                          return studentRepo.save(foundStudent);
                      })
                      .map(savedStudent -> studentConverter.fromDomain(savedStudent))
                      .orElseThrow(()-> new DataNotFoundException("Student with id " + id + " is not found"));
    }

    public void deleteStudent(Long id) {
       checkExisting(id);
       studentRepo.deleteById(id);
    }
    private void validate(StudentDto studentDto) {
        if(studentDto.getPhoneNum()<10){
            throw new SemanticException("Phone number should be larger than 10");
        }
    }
    private void checkExisting(Long id) {

        studentRepo
                .findById(id)
                .map(s ->{
                    System.out.println("checkingExisting student with id:" + id);
                    return s;
                })
                .orElseThrow(()->new DataNotFoundException("Student with id " + id + " is not found"));
    }

    public StudentDto getStudent(Long id) {
        Optional<Student> student = studentRepo.findById(id);
        return student.map(s -> studentConverter.fromDomain(s)).
                orElseThrow(()->new DataNotFoundException("Student with id " +  id + " is not found"));
    }

    public List<StudentDto> getStudentStartWith(String nameStartWith) {
        return studentRepo.findAllByNameStartsWith(nameStartWith)
                .stream()
                .map(student -> studentConverter.fromDomain(student))
                .toList();
    }
    public List<StudentDto> searchByName(String name) {
        return studentRepo.findMyStudents(name)
                .stream()
                .map(student -> studentConverter.fromDomain(student))
                .toList();
    }
}
