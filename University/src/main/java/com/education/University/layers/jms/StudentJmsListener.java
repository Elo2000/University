package com.education.University.layers.jms;

import com.education.University.layers.dto.StudentDto;
import com.education.University.layers.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class StudentJmsListener {
    private ObjectMapper objectMapper;
    private StudentService studentService;

    public StudentJmsListener(ObjectMapper objectMapper,StudentService studentService) {
        this.objectMapper = objectMapper;
        this.studentService = studentService;
    }

    @JmsListener(
            destination = "singlestudent"
    )
    public void readData(Message<String> message){
        JmsStudentDto jmsStudentDto= null;
        try {
            jmsStudentDto = objectMapper.readValue(message.getPayload(), JmsStudentDto.class);
            studentService.createStudent(new StudentDto(
                    jmsStudentDto.getStudentName(),
                    jmsStudentDto.isStudentGrad(),
                    jmsStudentDto.getStudentEmail(),
                    jmsStudentDto.getpNum()));
        } catch (JsonProcessingException e) {
            System.out.println("Message Data is not JSON .Raw data"+ message.getPayload());
        }
    }

    @JmsListener(
            destination = "updatesstudent"
    )
    public void updateData(Message<String> message) {
        JmsStudentDto jmsStudentDto = null;
        try {
            jmsStudentDto = objectMapper.readValue(message.getPayload(), JmsStudentDto.class);
            studentService.updateStudent(jmsStudentDto.getStudentId(),new StudentDto(
                    jmsStudentDto.getStudentName(),
                    jmsStudentDto.isStudentGrad(),
                    jmsStudentDto.getStudentEmail(),
                    jmsStudentDto.getpNum()));
        } catch (JsonProcessingException e) {
            System.out.println("Message Data is not JSON .Raw data" + message.getPayload());
        }
    }
}
