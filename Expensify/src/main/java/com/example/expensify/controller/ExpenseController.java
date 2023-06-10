package com.example.expensify.controller;

import com.example.expensify.entity.Expense;
import com.example.expensify.entity.Status;
import com.example.expensify.repository.ExpenseRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseController {

  private final ExpenseRepository expenseRepository;

  ExpenseController(ExpenseRepository expenseRepository) {
    this.expenseRepository = expenseRepository;
  }

  @PutMapping("/expenses/{expense_id}/status")
  public Expense review_expense(@RequestBody Status status, @PathVariable Long expense_id) {
    Expense expense = expenseRepository.findById(expense_id).orElseThrow();
    expense.getStatus().setState(status.getState());
    return expenseRepository.save(expense);
  }
}
