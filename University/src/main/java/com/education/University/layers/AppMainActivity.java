package com.education.University.layers;
import com.education.University.layers.domain.Student;
import com.education.University.layers.repository.StudentRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class AppMainActivity {
    private StudentRepository Repos;
    public AppMainActivity(StudentRepository usersRepository) {
        this.Repos = usersRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomething(){
        Optional<Student> stu = Repos.getStudent(3L);
        System.out.println("Student is :"+stu);

        Student newcreatedstudent=new Student("Samir ", 322654080L,false,"samir12@gmail.com",0547733222);
        Student createdStudent=Repos.createStudent(newcreatedstudent);
        System.out.println(createdStudent);

        Student studenttobeupdated=new Student("Samir update ",2L, true,"samir12@gmail.com",0547733222);
        Student updatedstudent=Repos.updateStudent(studenttobeupdated,2L).get();
        System.out.println(updatedstudent );

        Repos.deleteStudent(311543090L);
    }
}

