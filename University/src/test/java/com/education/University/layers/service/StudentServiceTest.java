package com.education.University.layers.service;

import com.education.University.layers.convert.StudentConverter;
import com.education.University.layers.domain.Student;
import com.education.University.layers.dto.StudentDto;
import com.education.University.layers.exceptions.DataNotFoundException;
import com.education.University.layers.repository.StudentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StudentServiceTest {
    @InjectMocks
    private StudentService studentService;
    @Mock
    private StudentRepo studentRepo;
    @Mock
    private StudentConverter studentConverter;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getStudent() {
        when(studentConverter.fromDomain(any())).thenCallRealMethod();
        when(studentRepo.findById(any())).thenReturn(Optional.of(new Student("Samir", false, "samir12@gmail.com", 94353042)));

        StudentDto student = studentService.getStudent(3L);
        System.out.println(student);

        assertNotNull(student);
        assertEquals("Samir",student.getName());
        assertFalse(student.isGraduated());
        assertEquals("samir12@gmail.com",student.getEmail());
        assertEquals(94353042,student.getPhoneNum());
    }

    @Test
    void getStudentThrowException() {
        DataNotFoundException dataNotFoundException=assertThrows(DataNotFoundException.class,()-> studentService.getStudent(120L));
        assertEquals("Student with id 120 is not found",dataNotFoundException.getMessage());

    }

   @Test
    void updateStudent(){
        when(studentRepo.findById(1L)).thenReturn(Optional.of(new Student("Samir",1L, false, "samir12@gmail.com", 94353042)));
        when(studentConverter.fromDomain(any())).thenCallRealMethod();
        when(studentConverter.fromDto(any())).thenCallRealMethod();
        StudentDto studentToBeUpdated=new StudentDto("helena", false, "samir179@gmail.com", 0547733222);

        when(studentRepo.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
    //   when(studentRepo.save(any())).thenThrow(new RuntimeException());
    //   when(studentRepo.save(any())).thenAnswer(invocation -> invocation );

        StudentDto updateStudent=studentService.updateStudent(1L, new StudentDto("Samir 2", true, "samir179@gmail.com", 0547733222));

        assertNotNull(updateStudent);
        assertEquals("Samir 2",updateStudent.getName());
        assertTrue(updateStudent.isGraduated());
        assertEquals("samir179@gmail.com",updateStudent.getEmail());
        assertEquals(0547733222,updateStudent.getPhoneNum());

    }

    @Test
    void updateStudentAndStudentDoesNotExist() {
        when(studentRepo.findById(888L)).thenReturn(Optional.empty());

        DataNotFoundException dataNotFoundException = assertThrows(DataNotFoundException.class, () -> studentService.updateStudent(888L, new StudentDto("Samir 2", true, "samir179@gmail.com", 0547733222)));

        assertEquals("Student with id 888 is not found",dataNotFoundException.getMessage());

       // verify(studentService, never()).updateStudent(any(),any(StudentDto.class));
        verify(studentRepo).findById(888L);
        verify(studentConverter, never()).fromDomain(any());
        verify(studentConverter, never()).fromDto(any());

    }



}

