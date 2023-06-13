package com.example.expensify.exceptionHandling;

public class ExpenseNotFoundException extends RuntimeException {
    public ExpenseNotFoundException(Long expenseId) {
        super("Expense not found with ID " + expenseId);
    }
}
