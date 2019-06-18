package com.example.employee.entity;


import com.example.employee.model.Gender;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Data
@Entity
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @SequenceGenerator(name="employee_id_seq", sequenceName="employee_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="employee_id_seq")
    private Long id;
    /**
     * A {@code Date} instance representing name of the employee.
     */
    private String name;
    /**
     * A {@code Date} instance representing date of birth.
     */
    private LocalDate dob;
    /**
     * A {@code Gender} instance representing gender
     */
    private Gender gender;
    /**
     * A {@code Date} instance that holds created date and this field cannot be updated in database.
     *
     * @see Temporal
     */
    @CreatedDate
    @Temporal(TIMESTAMP)
    @Column(updatable = false)
    protected Date createdDate;
    /**
     * A {@code Date} instance that holds last modified date.
     */
    @LastModifiedDate
    @Temporal(TIMESTAMP)
    protected Date modifiedDate;
}
