package com.education.University.layers.appevent;

import com.education.University.layers.domain.Student;
import com.education.University.layers.repository.StudentRepo;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JpaReadyListener {
    private final StudentRepo studentRepo;

    public JpaReadyListener(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doJpaWork(){
        Student student = studentRepo.findById(4L).get();

        Page<Student> studentRepoAll=studentRepo.findAll(Pageable.ofSize(5));
        Page<Student> studentReAll=studentRepo.findAll(PageRequest.of(2,5));

        studentRepoAll.getContent();
        studentRepoAll.getTotalPages();
        System.out.println(studentRepoAll);
        List<Student> sortedByNamesDesc= studentRepo.findAll(Sort.by(Sort.Direction.DESC,"name"));

        System.out.println(sortedByNamesDesc.get(0));// the first element desc
        System.out.println(sortedByNamesDesc.get(sortedByNamesDesc.size() - 1));//the last element

        System.out.println("Student from Spring data"+ student);

    }
}
