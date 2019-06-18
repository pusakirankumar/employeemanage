package com.example.employee.service;

import com.example.employee.config.EmployeeNotFoundException;
import com.example.employee.entity.EmployeeEntity;
import com.example.employee.model.Employee;
import com.example.employee.model.Gender;
import com.example.employee.repo.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class EmployeeServiceTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public EmployeeService employeeService() {
            return new EmployeeService();
        }
    }

    @Autowired
    EmployeeService employeeService;

    @MockBean
    EmployeeRepository employeeRepository;

    @Test
    public void testAddEmployee(){
        Employee employee = new Employee();
        employee.setName("Kiran");
        employee.setDob(LocalDate.of(2000,10,30));
        employee.setGender(Gender.FEMALE);
        EmployeeEntity e = new EmployeeEntity();
        e.setId(1l);
        e.setName("Kiran");
        Mockito.when(employeeRepository.save(Mockito.any(EmployeeEntity.class))).thenReturn(e);
        Employee returnedEmp = employeeService.addEmployee(employee);
        Assert.assertEquals( 1l, returnedEmp.getId().longValue());
        Mockito.verify(employeeRepository, Mockito.times(1)).save(Mockito.any(EmployeeEntity.class));
    }

    @Test
    public void testAddEmployees(){
        List<Employee> employeeList = new ArrayList<>();
        Employee employee = new Employee();
        employee.setName("Kiran");
        employee.setDob(LocalDate.of(2000,10,30));
        employee.setGender(Gender.FEMALE);
        employeeList.add(employee);
        EmployeeEntity e = new EmployeeEntity();
        e.setId(1l);
        e.setName("Kiran");
        Mockito.when(employeeRepository.save(Mockito.any(EmployeeEntity.class))).thenReturn(e);
        List<Employee> returnedEmp = employeeService.addEmployees(employeeList);
        returnedEmp.forEach( emp ->
        Assert.assertEquals( 1l, emp.getId().longValue()));
        Mockito.verify(employeeRepository, Mockito.times(1)).save(Mockito.any(EmployeeEntity.class));
    }

    @Test
    public void testModifyEmployee(){

        Employee employee = new Employee();
        employee.setId(1l);
        employee.setName("Kiran");
        employee.setDob(LocalDate.of(2000,10,30));
        employee.setGender(Gender.FEMALE);
        EmployeeEntity e = new EmployeeEntity();
        e.setId(1l);
        e.setName("KiranKumar");
        e.setDob(LocalDate.of(2000,10,30));
        e.setGender(Gender.MALE);
        Optional<EmployeeEntity> optional = Optional.of(e);
        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(optional);
        Mockito.when(employeeRepository.save(Mockito.any(EmployeeEntity.class))).thenReturn(e);
        Employee returnedEmp = employeeService.modifyEmployee(employee);

        Assert.assertEquals( e.getName(), returnedEmp.getName());
        Assert.assertEquals( e.getGender(), returnedEmp.getGender());
        Mockito.verify(employeeRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(employeeRepository, Mockito.times(1)).save(Mockito.any(EmployeeEntity.class));
    }

    @Test
    public void testModifyEmployeeWithException(){

        Employee employee = new Employee();
        employee.setId(1l);
        employee.setName("Kiran");
        employee.setDob(LocalDate.of(2000,10,30));
        employee.setGender(Gender.FEMALE);

        Optional<EmployeeEntity> optional = Optional.empty();
        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(optional);
        String errorMessage= "";
        try {
            Employee returnedEmp = employeeService.modifyEmployee(employee);
        }catch (EmployeeNotFoundException e) {
            errorMessage = e.getMessage();
        }
        Assert.assertEquals("Employee not found for employee id : 1",errorMessage);
        Mockito.verify(employeeRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(employeeRepository, Mockito.times(0)).save(Mockito.any(EmployeeEntity.class));
    }

    @Test
    public void testGetEmployee(){

        EmployeeEntity e = new EmployeeEntity();
        e.setId(1l);
        e.setName("KiranKumar");
        e.setDob(LocalDate.of(2000,10,30));
        e.setGender(Gender.MALE);
        Optional<EmployeeEntity> optional = Optional.of(e);
        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(optional);
        Employee returnedEmp = employeeService.getEmployee(1l);
        Assert.assertEquals( e.getName(), returnedEmp.getName());
        Assert.assertEquals( e.getGender(), returnedEmp.getGender());
        Mockito.verify(employeeRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    public void testGetEmployeeWithException(){

        Optional<EmployeeEntity> optional = Optional.empty();
        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(optional);
        String errorMessage= "";
        try {
            Employee returnedEmp = employeeService.getEmployee(1l);
        }catch (EmployeeNotFoundException e) {
            errorMessage = e.getMessage();
        }
        Assert.assertEquals("Employee not found for employee id : 1",errorMessage);
        Mockito.verify(employeeRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    public void testGetAllEmployees(){
        List<EmployeeEntity> employeeList = new ArrayList<>();
        EmployeeEntity e = new EmployeeEntity();
        e.setId(1l);
        e.setName("Kiran");
        e.setDob(LocalDate.of(2000,10,30));
        e.setGender(Gender.FEMALE);
        employeeList.add(e);
        e = new EmployeeEntity();
        e.setId(2l);
        e.setName("KiranKumar");
        e.setDob(LocalDate.of(2008,10,30));
        e.setGender(Gender.MALE);
        employeeList.add(e);
        Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);
        List<Employee> returnedEmp = employeeService.getAllEmployees(0);
        Assert.assertEquals(2, returnedEmp.size());
        Assert.assertEquals( employeeList.get(0).getName(), returnedEmp.get(0).getName());
        Assert.assertEquals( employeeList.get(1).getName(), returnedEmp.get(1).getName());
        Mockito.verify(employeeRepository, Mockito.times(1)).findAll();
    }
}
