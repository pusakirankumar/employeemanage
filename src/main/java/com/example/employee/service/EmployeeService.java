package com.example.employee.service;

import com.example.employee.config.EmployeeNotFoundException;
import com.example.employee.entity.EmployeeEntity;
import com.example.employee.model.Employee;
import com.example.employee.repo.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class EmployeeService implements IEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> addEmployees(List<Employee> employees) {
        return employees.stream()
                .map( e -> addEmployee(e))
                .collect(Collectors.toList());
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return fromEntity(employeeRepository.save(toEntity(employee)));
    }

    @Override
    public Employee modifyEmployee(Employee employee) {
        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(employee.getId());
        if(optionalEmployeeEntity.isPresent()){
           return fromEntity(employeeRepository.save(toEntity(employee, optionalEmployeeEntity.get())));
        }
        throw new EmployeeNotFoundException("Employee not found for employee id : "+employee.getId());
    }

    @Override
    public Employee getEmployee(Long id) {
        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(id);
        if(optionalEmployeeEntity.isPresent()){
            return fromEntity(optionalEmployeeEntity.get());
        }
        throw new EmployeeNotFoundException("Employee not found for employee id : "+id);
    }

    @Override
    public List<Employee> getAllEmployees(Integer age) {
        if(age == null || age == 0){
            return fromEntityList(employeeRepository.findAll());
        }

        return fromEntityList(employeeRepository.findAllEmployeesWhoseAgeGreaterThan(getPreviousDate(age)));
    }

    private EmployeeEntity toEntity(Employee employee){
        return toEntity(employee, null);
    }

    private EmployeeEntity toEntity(Employee employee, EmployeeEntity employeeEntity){
        if(employeeEntity==null)
            employeeEntity = new EmployeeEntity();

        employeeEntity.setId(employee.getId());
        employeeEntity.setName(employee.getName());
        employeeEntity.setDob(employee.getDob());
        employeeEntity.setGender(employee.getGender());

        return employeeEntity;
    }

    private Employee fromEntity(EmployeeEntity employeeEntity){
        Employee employee = new Employee();
        employee.setId(employeeEntity.getId());
        employee.setName(employeeEntity.getName());
        employee.setDob(employeeEntity.getDob());
        employee.setGender(employeeEntity.getGender());
        return employee;
    }
    private List<Employee> fromEntityList(List<EmployeeEntity> employeeEntities){
        return employeeEntities.stream().map( e -> fromEntity(e)).collect(Collectors.toList());
    }

    private LocalDate getPreviousDate(Integer age){
        LocalDate date = LocalDate.now();

        LocalDate previousDate = LocalDate.of((date.getYear()-age), date.getMonth(),date.getDayOfMonth());
        log.info("Current date is {}, age is {} and previous date is {}", date, age, previousDate);
        return  previousDate;
    }
}
