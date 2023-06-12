package com.example.expensify.controller;

import com.example.expensify.entity.Employee;
import com.example.expensify.entity.Expense;
import com.example.expensify.repository.EmployeeRepository;
import com.example.expensify.repository.ExpenseRepository;
import com.example.expensify.repository.StatusRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

  private final EmployeeRepository employeeRepository;
  private final ExpenseRepository expenseRepository;
  private final StatusRepository statusRepository;

  EmployeeController(
      EmployeeRepository employeeRepository,
      ExpenseRepository expenseRepository,
      StatusRepository statusRepository) {
    this.employeeRepository = employeeRepository;
    this.expenseRepository = expenseRepository;
    this.statusRepository = statusRepository;
  }

  @GetMapping("/employees/{employee_id}/expenses")
  public List<Expense> get_employee_expenses(@PathVariable Long employeeId) {
    return employeeRepository.findById(employeeId).orElseThrow().getExpenses();
  }

  @PostMapping("/employees/{employee_id}/expenses")
  public Expense post_employee_expense(
      @PathVariable Long employeeId, @RequestBody Expense newExpense) {
    Employee employee = employeeRepository.findById(employeeId).orElseThrow();
    newExpense =
        Expense.builder()
            .merchant(newExpense.getMerchant())
            .amount(newExpense.getAmount())
            .description(newExpense.getDescription())
            .purchaseDate(newExpense.getPurchaseDate())
            .status(statusRepository.save(newExpense.getStatus()))
            .build();
    employee.getExpenses().add(newExpense);
    employeeRepository.save(employee);
    return newExpense;
  }

  @DeleteMapping("/employees/{employee_id}/expenses/{expense_id}")
  public Expense delete_expense(@PathVariable Long employeeId, @PathVariable Long expenseId) {
    Employee employee = employeeRepository.findById(employeeId).orElseThrow();
    Expense expense = expenseRepository.findById(expenseId).orElseThrow();
    employee.getExpenses().remove(expense);
    employeeRepository.save(employee);
    expenseRepository.delete(expense);
    return expense;
  }

  @GetMapping("/employees")
  public List<Employee> get_all_employees() {
    return employeeRepository.findAll();
  }
}
