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

  @PutMapping("/expenses/{expense_id}/status")
  public Expense review_expense(@RequestBody Status status, @PathVariable Long expense_id) {
    Expense expense = expenseRepository.findById(expense_id).orElseThrow();
    expense =
        Expense.builder()
            .id(expense_id)
            .merchant(expense.getMerchant())
            .amount(expense.getAmount())
            .description(expense.getDescription())
            .purchaseDate(expense.getPurchaseDate())
            .status(statusRepository.save(status))
            .build();
    return expenseRepository.save(expense);
  }
}
