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

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "employee_id")
  private List<Role> roles;

  protected Employee() {}

  public Employee(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public Long getId() {
    return this.id;
  }

  public List<Expense> getExpenses() {
    return this.expenses;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public List<Role> getAuthorities() {
    return this.roles;
  }
}
