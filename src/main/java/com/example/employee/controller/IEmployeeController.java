package com.example.employee.controller;


import com.example.employee.model.Employee;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "Employee Details",
        tags = {"Employee Management System:"})
@RequestMapping("/employee")
public interface IEmployeeController {

    /**
     * Adds employee.
     *
     * @param employee employee details to add
     * @return created employee
     */
    @ApiOperation(value = "Adds a employee")
    @PostMapping("/add")
    Employee addEmployee(@RequestBody Employee employee);

    /**
     * Adds multiple employee.
     *
     * @param employees employees details to add
     * @return created employee list
     */
    @ApiOperation(value = "Adds multiple employees")
    @PostMapping("/add/multiple")
    List<Employee> addEmployees(@RequestBody List<Employee> employees);

    /**
     * Modify employee.
     *
     * @param employee employee details to modify
     * @return Modified employee
     */
    @ApiOperation(value = "Modifies a employee")
    @PutMapping("/modify")
    Employee modifyEmployee(@RequestBody Employee employee);

    /**
     * Retrieves all employees if age is not supplied,
     * if age supplied then retrieves all the employees who are grater than specified age
     *
     * @param age filter condition
     * @return list of Employees
     */
    @ApiOperation(value = "Retrieves all employees if age is not supplied, " +
            "if age supplied then retrieves all the employees who are grater than specified age")
    @GetMapping("/all")
    List<Employee> getAllEmployees(@RequestParam(required = false) Integer age);

    /**
     * Retrieves employee by id
     *
     * @param id id of the employee
     * @return Employees
     */
    @ApiOperation(value = "Retrieves one employee by id")
    @GetMapping("/{id}")
    Employee getEmployee(@PathVariable Long id);

}
