package com.example.employee.controller;

import com.example.employee.model.Employee;
import com.example.employee.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController implements IEmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee addEmployee(Employee employee) {
        return employeeService.addEmployee(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> addEmployees(List<Employee> employees) {
        return employeeService.addEmployees(employees);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee modifyEmployee(Employee employee) {
        return employeeService.modifyEmployee(employee);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> getAllEmployees(Integer age) {
        return employeeService.getAllEmployees(age);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Employee getEmployee(Long id) {
        return employeeService.getEmployee(id);
    }
}
