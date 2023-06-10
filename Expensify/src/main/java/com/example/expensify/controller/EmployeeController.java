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
  public List<Expense> get_employee_expenses(@PathVariable Long employee_id) {
    return employeeRepository.findById(employee_id).orElseThrow().getExpenses();
  }

  @PostMapping("/employees/{employee_id}/expenses")
  public Expense get_employee_expenses(
      @PathVariable Long employee_id, @RequestBody Expense newExpense) {
    Employee employee = employeeRepository.findById(employee_id).orElseThrow();
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
  public Expense delete_expense(@PathVariable Long employee_id, @PathVariable Long expense_id) {
    Employee employee = employeeRepository.findById(employee_id).orElseThrow();
    Expense expense = expenseRepository.findById(expense_id).orElseThrow();
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
