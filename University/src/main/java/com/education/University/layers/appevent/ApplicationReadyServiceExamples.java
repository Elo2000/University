package com.education.University.layers.appevent;

import com.education.University.layers.dto.StudentDto;
import com.education.University.layers.exceptions.ApplicationException;
import com.education.University.layers.service.StudentService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

public class ApplicationReadyServiceExamples {
    private StudentService studentService;

    public ApplicationReadyServiceExamples(StudentService studentService) {
        this.studentService = studentService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        try {
            StudentDto student;
            student = studentService.createStudent(new StudentDto("Helena", true, "Helena.kh@gmail.com", 288));
            System.out.println(student);
        } catch (ApplicationException e) {
            System.out.println(e.getMessage());
        }

        try {
            studentService.deleteStudent(2L);
        } catch (ApplicationException e) {
            System.out.println(e.getMessage());
        }
    }
}
