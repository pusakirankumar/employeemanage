package com.example.employee.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
/**
 * This class holds the details of Employee.
 *
 * @see Data
 */
@Data
@ApiModel("Employee details")
public class Employee {

    /**
     * Long identifier
     */
    @ApiModelProperty(notes = "The database generated employee ID")
    private Long id;
    /**
     * A {@code String} instance representing employee name.
     */
    @NotNull
    @ApiModelProperty(notes = "Represents Employee Name")
    private String name;
    /**
     * A {@code LocalDate} instance representing employee date of birth.
     */
    @NotNull
    @ApiModelProperty(notes = "Represents Employee date of birth")
    private LocalDate dob;
    /**
     * A {@code Gender} instance representing employee gender.
     */
    @NotNull
    @ApiModelProperty(notes = "Represents Employee gender")
    private Gender gender;
}
