package com.example.employee.config;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public final ResponseEntity<String> handleResourceNotFound(final EmployeeNotFoundException ex,
                                                               final HttpServletRequest request) {

        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("status" , HttpStatus.NOT_FOUND.value() );
        node.put("message", ex.getMessage());

        return new ResponseEntity<>(node.toString(), HttpStatus.NOT_FOUND);
    }

}