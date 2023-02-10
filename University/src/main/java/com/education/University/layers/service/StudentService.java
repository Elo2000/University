package com.education.University.layers.service;

import com.education.University.layers.dto.StudentDto;
import com.education.University.layers.convert.StudentConverter;
import com.education.University.layers.domain.Student;
import com.education.University.layers.exceptions.DataNotFoundException;
import com.education.University.layers.exceptions.SemanticException;
import com.education.University.layers.jms.JmsStudentDeleteDto;
import com.education.University.layers.jms.JmsStudentSender;
import com.education.University.layers.repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//this class is used for logic
@Service
public class StudentService {
    private StudentRepo studentRepo;
    //private StudentRepository repository;
    private JmsStudentSender sender;

    private StudentConverter studentConverter;
    private JmsStudentSender notifier;

    public StudentService(StudentConverter studentConverter, StudentRepo studentRepo, JmsStudentSender sender) {
       // System.out.println("Constructor is called");
      //  this.repository = repository;
        this.studentConverter = studentConverter;
        this.studentRepo=studentRepo;
        this.sender = sender;
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
       StudentDto studentToDelete=checkExisting(id);
       studentRepo.deleteById(id);
        notifier.sendDeleteMessage(new JmsStudentDeleteDto(
               studentToDelete.getPhoneNum().toString(),
               studentToDelete.getId()));
    }
    private void validate(StudentDto studentDto) {
        if(studentDto.getPhoneNum()<10){
            throw new SemanticException("Phone number should be larger than 10");
        }
    }
    private StudentDto checkExisting(Long id) {
         return studentRepo
                .findById(id)
                .map(s ->{
                    System.out.println("checkingExisting student with id:" + id);
                    return s;
                })
                .map(s ->studentConverter.fromDomain(s))
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
