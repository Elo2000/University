package com.education.University.layers.dto;

public class StudentDto {
    private String name;
    private long id;
    private boolean graduated;
    private String email;
    private Integer phoneNum;

    public StudentDto(String name, boolean graduated, String email, Integer phoneNum) {
        this.name = name;
        this.id = id;
        this.graduated = graduated;
        this.email = email;
        this.phoneNum = phoneNum;
    }
    public StudentDto(){
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
}
