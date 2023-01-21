package com.education.University.layers.controller;

import com.education.University.layers.dto.StudentDto;
import com.education.University.layers.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// it is used for logic
@RestController
@RequestMapping("/students")
public class StudentController {
    private StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // localhost - 127.0.0.1
    // port 5432 -- postgres
    // GET http://localhost:8080/students/<ID of particular student>
    @RequestMapping(method = RequestMethod.GET,path = "/{id}")
    public StudentDto getStudent(@PathVariable Long id){
        return studentService.getStudent(id);
    }

    // REST best practices
    // GET http://localhost:8080/students/
    @RequestMapping(method = RequestMethod.GET)
    public List<StudentDto> getStudents(@RequestParam("nameStartWith")String nameStartWith) {
        if(nameStartWith==null){
            return  studentService.getStudents();
        }
        else {
            return studentService.getStudentStartWith(nameStartWith);
        }
    }
    @RequestMapping(path ="/search", method = RequestMethod.GET)
    public List<StudentDto> search(@RequestParam("name") String name) {
        return studentService.searchByName(name);
    }

    // POST http://localhost:8080/students/
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDto createStudent(@RequestBody StudentDto studentDto){
        return studentService.createStudent(studentDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletestudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public StudentDto updateStudent(@PathVariable Long id,@RequestBody StudentDto studentDto) {
        return studentService.updateStudent(id, studentDto);
    }



}
