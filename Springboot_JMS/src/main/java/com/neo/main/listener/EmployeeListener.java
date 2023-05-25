package com.neo.main.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.neo.main.model.Employee;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmployeeListener {
	
	@JmsListener(destination = "EmpTopic", containerFactory = "empJmsContFactory")
    public void getEmployeeListener1(Employee emp) {
        log.info("Employee listener1: " + emp);
    }
	
	@JmsListener(destination = "EmpTopic", containerFactory = "empJmsContFactory")
    public void getEmployeeListener2(Employee emp) {
        log.info("Employee listener2: " + emp);
    }
	@JmsListener(destination = "EmpTopic", containerFactory = "empJmsContFactory")
    public void getEmployeeListener3(Employee emp) {
        log.info("Employee listener3: " + emp);
    }

}
