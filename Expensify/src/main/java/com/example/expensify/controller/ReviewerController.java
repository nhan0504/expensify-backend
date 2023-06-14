package com.example.expensify.controller;

import com.example.expensify.entity.Employee;
import com.example.expensify.entity.Expense;
import com.example.expensify.entity.Status;
import com.example.expensify.exception.ExpenseNotFoundException;
import com.example.expensify.repository.EmployeeRepository;
import com.example.expensify.repository.ExpenseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewerController {

  private final ExpenseRepository expenseRepository;
  private final EmployeeRepository employeeRepository;

  public ReviewerController(ExpenseRepository expenseRepository, EmployeeRepository employeeRepository) {
    this.expenseRepository = expenseRepository;
    this.employeeRepository = employeeRepository;
  }

  @GetMapping("/employees")
  public List<Employee> getEmployees() {
    return employeeRepository.findAll();
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PutMapping("/expenses/{expenseId}/status")
  public void reviewExpense(@RequestBody Status status, @PathVariable Long expenseId) {
    Expense expense =
        expenseRepository
            .findById(expenseId)
            .orElseThrow(() -> new ExpenseNotFoundException(expenseId));
    expense.getStatus().changeTo(status);
    expenseRepository.save(expense);
  }
}