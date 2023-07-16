package com.example.expensify.exception;

public class ExpenseNotFoundException extends RuntimeException {

  public ExpenseNotFoundException(Long expenseId) {
    super("Expense not found with ID " + expenseId);
  }
}
