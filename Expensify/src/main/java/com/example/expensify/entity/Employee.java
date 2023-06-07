package com.example.expensify.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "employees")
public class Employee extends ExpensifyUser {
  @Id @GeneratedValue private Long id;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(
      name = "employees_expenses",
      joinColumns = {@JoinColumn(name = "employee_id")},
      inverseJoinColumns = {@JoinColumn(name = "expense_id")})
  private List<Expense> expenses;

  private String username;

  private String password;

  private Role role;

  protected Employee() {}

  public Employee(String username, String password, Role role) {
    super(username, password, role);
    this.username = username;
    this.password = password;
    this.role = role;
  }

  public Long getId() {
    return this.id;
  }

  public List<Expense> getExpenses() {
    return this.expenses;
  }
}
