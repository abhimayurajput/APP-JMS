
package com.neo.main.controller;

import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neo.main.model.Employee;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class EmployeeController {
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@PostMapping("/employee")
	  public ResponseEntity<Employee> newEmployee(@RequestBody Employee employee) {
        try {
            Topic empTopic = jmsTemplate.getConnectionFactory().createConnection()
                    .createSession().createTopic("EmpTopic");
            int empId = (int)(Math.random() * 50 + 1);
            Employee emp = Employee.builder().id(empId).name(employee.getName()).role(employee.getRole()).build();
            log.info("Sending Employee Object: " + emp);
            jmsTemplate.convertAndSend(empTopic, emp);
            return new ResponseEntity<>(emp, HttpStatus.OK);

        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
