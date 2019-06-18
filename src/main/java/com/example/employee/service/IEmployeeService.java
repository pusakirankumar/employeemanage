package com.example.employee.service;

import com.example.employee.model.Employee;

import java.util.List;

public interface IEmployeeService {

    List<Employee> addEmployees(List<Employee> employees);
    Employee addEmployee(Employee employee);
    Employee modifyEmployee(Employee employee);
    Employee getEmployee(Long id);
    List<Employee> getAllEmployees(Integer age);
}
