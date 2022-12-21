package com.education.University.layers.domain;
// object in data storage

import java.util.Objects;

public class Student {
    private String name;
    private Long id;
    private boolean graduated;
    private String email;
    private Integer phoneNum;

    public Student(String name, boolean graduated, String email, Integer phoneNum) {
    }

    public Student(String name, Long id, boolean graduated, String email, Integer phoneNum) {
        this.name = name;
        this.id = id;
        this.graduated = graduated;
        this.email = email;
        this.phoneNum = phoneNum;
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
        return phoneNum;
    }

    public void setPhoneNum(Integer phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return graduated == student.graduated && Objects.equals(name, student.name) && Objects.equals(id, student.id) && Objects.equals(email, student.email) && Objects.equals(phoneNum, student.phoneNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, graduated, email, phoneNum);
    }
    @Override
    public String toString() {
        return "student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", graduated=" + graduated +
                ", email='" + email + '\'' +
                ", phoneNum=" + phoneNum +
                '}';
    }


}
