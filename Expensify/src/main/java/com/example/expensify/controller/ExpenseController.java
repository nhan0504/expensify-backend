package com.example.expensify.controller;

import com.example.expensify.entity.Expense;
import com.example.expensify.entity.Status;
import com.example.expensify.repository.ExpenseRepository;
import com.example.expensify.repository.StatusRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseController {

  private final ExpenseRepository expenseRepository;
  private final StatusRepository statusRepository;

  ExpenseController(ExpenseRepository expenseRepository, StatusRepository statusRepository) {
    this.expenseRepository = expenseRepository;
    this.statusRepository = statusRepository;
  }

  @PutMapping("/expenses/{expenseId}/status")
  public Expense review_expense(@RequestBody Status status, @PathVariable Long expenseId) {
    Expense expense = expenseRepository.findById(expenseId).orElseThrow();
    Status newStatus = expense.getStatus().changeState(status.getState());
    expense = expense.changeStatus(statusRepository.save(newStatus));
    return expenseRepository.save(expense);
  }
}
