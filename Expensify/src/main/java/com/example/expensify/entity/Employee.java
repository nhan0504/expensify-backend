package com.example.expensify.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee extends ExpensifyUser {

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Expense> expenses;

  protected Employee() {}

  public Employee(String username, String password, Role role) {
    super(username, password, role);
    this.expenses = new ArrayList<>();
  }

  public List<Expense> getExpenses() {
    return this.expenses;
  }
}
