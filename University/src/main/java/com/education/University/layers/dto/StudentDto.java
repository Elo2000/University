package com.education.University.layers.dto;

import java.util.Objects;

public class StudentDto {
    private String name;
    private long id;
    private boolean graduated;
    private String email;
    private Integer phoneNum;

    public StudentDto(String name,Long id, boolean graduated, String email, Integer phoneNum) {
        this.name = name;
        this.id = id;
        this.graduated = graduated;
        this.email = email;
        this.phoneNum = phoneNum;
    }
    public StudentDto(String name, boolean graduated, String email, Integer phoneNum){
        this.name = name;
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

    public void setId(Long id) {
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
    public String toString() {
        return "StudentDto{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", graduated=" + graduated +
                ", email='" + email + '\'' +
                ", phoneNum=" + phoneNum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDto that = (StudentDto) o;
        return id == that.id && graduated == that.graduated && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(phoneNum, that.phoneNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, graduated, email, phoneNum);
    }
}
