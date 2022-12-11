package com.education.University.layers;
import com.education.University.layers.domain.student;
import com.education.University.layers.repository.StudentRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AppMainActivity {
    private StudentRepository Repos;
    public AppMainActivity(StudentRepository usersRepository) {
        this.Repos = usersRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomething(){
        student stu = Repos.getstudent(311678060L);
        System.out.println("Student is :"+stu);
    }
}

