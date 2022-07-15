package com.payroll.spring_rest_tutorial.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Long id) {
        super("Employee \"" + id + "\" can't be found");
    }
}
