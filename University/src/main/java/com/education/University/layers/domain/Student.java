package com.education.University.layers.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Students")
public class Student {
    private String name;
    @Id
    @GeneratedValue(generator = "students_id_seq")
    private Long id;
    private boolean graduated;
    private String email;
   @Column(name = "Phone_number")
    private Integer phoneNumSS;

    @Transient
    private String anotherField;

    // Default Constructor is mandatory in hibernate entity
    public Student() {
    }

    public Student(String name, boolean graduated, String email, Integer phoneNum) {
        this.name = name;
        this.graduated = graduated;
        this.email = email;
        this.phoneNumSS = phoneNum;
        this.id = id;

    }

    public Student(String name, Long id, boolean graduated, String email, Integer phoneNum) {
        this.name = name;
        this.id = id;
        this.graduated = graduated;
        this.email = email;
        this.phoneNumSS = phoneNum;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isGraduated() {
        return graduated;
    }

    public void setGraduated(boolean graduated) {
        this.graduated = graduated;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhoneNum() {
        return phoneNumSS;
    }

    public void setPhoneNum(Integer phoneNum) {
        this.phoneNumSS = phoneNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return graduated == student.graduated && Objects.equals(name, student.name) && Objects.equals(id, student.id) && Objects.equals(email, student.email) && Objects.equals(phoneNumSS, student.phoneNumSS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, graduated, email, phoneNumSS);
    }
    @Override
    public String toString() {
        return "student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", graduated=" + graduated +
                ", email='" + email + '\'' +
                ", phoneNum=" + phoneNumSS +
                '}';
    }


}
