package com.example.employee.repo;

import com.example.employee.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    @Query("SELECT e from EmployeeEntity e where e.dob <= :enteredDate")
    List<EmployeeEntity> findAllEmployeesWhoseAgeGreaterThan(@Param("enteredDate") LocalDate enteredDate);
}
