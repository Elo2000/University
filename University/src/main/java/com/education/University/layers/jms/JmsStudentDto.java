package com.education.University.layers.jms;

public class JmsStudentDto {
    private String studentName;
    private long studentId;
    private boolean studentGrad;
    private String studentEmail;
    private Integer pNum;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public boolean isStudentGrad() {
        return studentGrad;
    }

    public void setStudentGrad(boolean studentGrad) {
        this.studentGrad = studentGrad;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public Integer getpNum() {
        return pNum;
    }

    public void setpNum(Integer pNum) {
        this.pNum = pNum;
    }
}
