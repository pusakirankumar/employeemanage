package com.example.employee.config;

import lombok.Data;


@Data
public class EmployeeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -5246884075098444372L;

    public EmployeeNotFoundException() {
        super();
    }

    public EmployeeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeNotFoundException(Throwable cause) {
        super(cause);
    }

    protected EmployeeNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
