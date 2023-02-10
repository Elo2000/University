package com.education.University.layers.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.core.JmsTemplate;

public class JmsStudentSender {

    private JmsTemplate jmsTemplate;
    private ObjectMapper objectMapper;

    public JmsStudentSender(JmsTemplate jmsTemplate, ObjectMapper objectMapper) {
        this.jmsTemplate = jmsTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendDeleteMessage(Object data){
        //jmsTemplate.send("notifyQueue",session -> new ActiveMQMessage());
        try {
            jmsTemplate.convertAndSend("AppDeleteStudent",objectMapper.writeValueAsString(data));
        } catch (JsonProcessingException e) {
            System.out.println("There is an issue to convert dat to json");
        }
    }
}
