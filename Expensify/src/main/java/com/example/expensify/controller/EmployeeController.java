package com.example.expensify.controller;

import com.example.expensify.entity.Employee;
import com.example.expensify.entity.Expense;
import com.example.expensify.exceptionHandling.EmployeeNotFoundException;
import com.example.expensify.repository.EmployeeRepository;
import com.example.expensify.repository.ExpenseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class EmployeeController {

  private final EmployeeRepository employeeRepository;
  private final ExpenseRepository expenseRepository;

  EmployeeController(EmployeeRepository employeeRepository, ExpenseRepository expenseRepository) {
    this.employeeRepository = employeeRepository;
    this.expenseRepository = expenseRepository;
  }

  @GetMapping("/employees/{employeeId}/expenses")
  public List<Expense> getEmployeeExpenses(@PathVariable Long employeeId) {
    return employeeRepository
        .findById(employeeId)
        .orElseThrow(() -> new EmployeeNotFoundException(employeeId))
        .getExpenses();
  }

  @PostMapping("/employees/{employeeId}/expenses")
  public Expense postEmployeeExpense(
      @PathVariable Long employeeId, @RequestBody Expense newExpense) {
    Employee employee =
        employeeRepository
            .findById(employeeId)
            .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
    employee.getExpenses().add(newExpense);
    employeeRepository.save(employee);
    return newExpense;
  }

  @DeleteMapping("/employees/{employeeId}/expenses/{expenseId}")
  public Expense deleteEmployeeExpense(
      @PathVariable Long employeeId, @PathVariable Long expenseId) {
    Employee employee =
        employeeRepository
            .findById(employeeId)
            .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
    Expense expense = expenseRepository.findById(expenseId).orElseThrow();
    employee.getExpenses().remove(expense);
    employeeRepository.save(employee);
    return expense;
  }
}
