package com.example.expensify.exception;

public class EmployeeNotFoundException extends RuntimeException {

  public EmployeeNotFoundException(Long employeeId) {
    super("Employee not found with ID " + employeeId);
  }
}
