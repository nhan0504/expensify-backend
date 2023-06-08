package com.example.expensify.entity;

import org.springframework.data.jpa.repository.JpaRepository;

interface ExpenseRepository extends JpaRepository<Expense, Long> {}
