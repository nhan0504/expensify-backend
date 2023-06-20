package com.example.expensify.controller;

import com.example.expensify.entity.Employee;
import com.example.expensify.entity.Expense;
import com.example.expensify.entity.State;
import com.example.expensify.entity.Status;
import com.example.expensify.exception.EmployeeNotFoundException;
import com.example.expensify.exception.ExpenseNotFoundException;
import com.example.expensify.repository.EmployeeRepository;
import com.example.expensify.repository.ExpenseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

  private final EmployeeRepository employeeRepository;
  private final ExpenseRepository expenseRepository;

  public EmployeeController(
      EmployeeRepository employeeRepository, ExpenseRepository expenseRepository) {
    this.employeeRepository = employeeRepository;
    this.expenseRepository = expenseRepository;
  }

  @PreAuthorize("#employeeId == principal.getId()")
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
    newExpense.setStatus(Status.builder().state(State.IN_REVIEW).build());
    Employee employee =
        employeeRepository
            .findById(employeeId)
            .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
    employee.getExpenses().add(newExpense);
    List<Expense> list = employeeRepository.save(employee).getExpenses();
    return list.get(list.size() - 1);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/employees/{employeeId}/expenses/{expenseId}")
  public void deleteEmployeeExpense(@PathVariable Long employeeId, @PathVariable Long expenseId) {
    Employee employee =
        employeeRepository
            .findById(employeeId)
            .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
    Expense expense =
        expenseRepository
            .findById(expenseId)
            .orElseThrow(() -> new ExpenseNotFoundException(expenseId));
    employee.getExpenses().remove(expense);
    employeeRepository.save(employee);
  }
}
