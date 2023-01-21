package com.education.University.layers.service;

import com.education.University.layers.convert.StudentConverter;
import com.education.University.layers.domain.Student;
import com.education.University.layers.dto.StudentDto;
import com.education.University.layers.exceptions.DataNotFoundException;
import com.education.University.layers.repository.StudentRepo;
import com.education.University.layers.repository.StudentRepository;
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
        System.out.println("S"+student);

        assertNotNull(student);
        System.out.println("SName"+student.getName());
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
        when(studentRepo.findById(1L)).thenReturn(Optional.of(new Student("Samir", false, "samir12@gmail.com", 94353042)));
        when(studentConverter.fromDomain(any())).thenCallRealMethod();
        when(studentConverter.fromDto(any())).thenCallRealMethod();
        StudentDto studentToBeUpdated=new StudentDto("helena", false, "samir179@gmail.com", 0547733222);

        when(studentService.updateStudent(1L,studentToBeUpdated)).thenReturn(studentToBeUpdated);
        when(studentService.updateStudent(2L,studentToBeUpdated)).thenThrow(new RuntimeException());
        when(studentService.updateStudent(3L,studentToBeUpdated)).thenAnswer(invocation -> invocation );

        StudentDto updateStudent=studentService.updateStudent(1L, new StudentDto("Samir 2", true, "samir179@gmail.com", 0547733222));

        assertNotNull(updateStudent);
        assertEquals("Samir 2",updateStudent.getName());
        assertTrue(updateStudent.isGraduated());
        assertEquals("samir179@gmail.com",updateStudent.getEmail());
        assertEquals(0547733222,updateStudent.getPhoneNum());

    }

//    @Test
//    void updateStudentAndStudentDoesNotExist() {
//        when(studentRepo.findById(888L)).thenReturn(Optional.empty());
//
//        DataNotFoundException dataNotFoundException = assertThrows(DataNotFoundException.class, () -> studentService.updateStudent(888L, new StudentDto("Samir 2", true, "samir179@gmail.com", 0547733222)));
//
//        assertEquals("Student with id888 is not found",dataNotFoundException.getMessage());
//
//        verify(studentRepo, never()).updateStudent(any(),anyLong());
//        verify(studentRepo).findById(888L);
//        verify(studentConverter, never()).fromDomain(any());
//        verify(studentConverter, never()).fromDto(any());
//
//    }



}




// the old legacy
//    static class StudentRepositoryMock extends StudentRepository{
//
//        public StudentRepositoryMock(JdbcTemplate jdbcTemplate, RowMapper<Student> StudentRowMapper1) {
//            super(jdbcTemplate, StudentRowMapper1);
//        }
//
//        @Override
//        public Optional<Student> getStudent(Long id) {
//            if(id==3L){
//                return Optional.of(new Student("Samir",false,"samir12@gmail.com",94353042));
//            }
//            return Optional.empty() ;
//        }
//
//        @Override
//        public Optional<Student> updateStudent(Student student, Long id) {
//            return super.updateStudent(student, id);
//        }
//    }
//}