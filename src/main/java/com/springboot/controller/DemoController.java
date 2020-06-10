package com.springboot.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.EmployeeModel;

@RestController
@RequestMapping("/api/test")
public class DemoController {

    @GetMapping("/employee/{empId}")
    public EmployeeModel getCustomerProfile(@PathVariable("empId") String empId) {
        return getEmployee(empId);
    }

    private EmployeeModel getEmployee(final String empId) {
    	EmployeeModel emp = new EmployeeModel();
    	emp.setEmpName("Demo");
    	emp.setEmpId(String.valueOf(empId));
    	emp.setEmail("test@gmail.com");
    	emp.setAddress("chennai");
    	emp.setContactNumber("98765431");
        return emp;
    }
}
