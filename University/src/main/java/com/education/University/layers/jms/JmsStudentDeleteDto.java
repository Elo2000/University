package com.education.University.layers.jms;

public class JmsStudentDeleteDto {

    private String details;
    private Long id;

    public JmsStudentDeleteDto(String details, Long id) {
        this.details = details;
        this.id = id;
    }

    public JmsStudentDeleteDto() {
    }
}
