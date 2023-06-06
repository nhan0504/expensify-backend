package com.example.expensify.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "reviewers")
public class Reviewer extends ExpensifyUser {
  @Id @GeneratedValue private Long id;

  private String username;

  private String password;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "employee_id")
  private List<Role> roles;

  protected Reviewer() {}

  public Reviewer(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public Long getId() {
    return this.id;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public List<Role> getAuthorities() {
    return this.roles;
  }
}
