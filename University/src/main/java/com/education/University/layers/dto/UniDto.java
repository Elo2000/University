package com.education.University.layers.dto;

import java.util.Objects;

public class UniDto {
    private String UniName;
    private Integer NumOfStudents;
    private Integer NumOfTeachers;
    private String UniNum;

    public UniDto() {
    }

    public UniDto(String uniName, Integer numOfStudents, Integer numOfTeachers, String uniNum) {
        UniName = uniName;
        NumOfStudents = numOfStudents;
        NumOfTeachers = numOfTeachers;
        UniNum = uniNum;
    }

    public String getUniName() {
        return UniName;
    }

    public void setUniName(String uniName) {
        UniName = uniName;
    }

    public Integer getNumOfStudents() {
        return NumOfStudents;
    }

    public void setNumOfStudents(Integer numOfStudents) {
        NumOfStudents = numOfStudents;
    }

    public Integer getNumOfTeachers() {
        return NumOfTeachers;
    }

    public void setNumOfTeachers(Integer numOfTeachers) {
        NumOfTeachers = numOfTeachers;
    }

    public String getUniNum() {
        return UniNum;
    }

    public void setUniNum(String uniNum) {
        UniNum = uniNum;
    }

    @Override
    public String toString() {
        return "UniDto{" +
                "UniName='" + UniName + '\'' +
                ", NumOfStudents=" + NumOfStudents +
                ", NumOfTeachers=" + NumOfTeachers +
                ", UniNum='" + UniNum + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniDto uniDto = (UniDto) o;
        return Objects.equals(UniName, uniDto.UniName) && Objects.equals(NumOfStudents, uniDto.NumOfStudents) && Objects.equals(NumOfTeachers, uniDto.NumOfTeachers) && Objects.equals(UniNum, uniDto.UniNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(UniName, NumOfStudents, NumOfTeachers, UniNum);
    }
}
