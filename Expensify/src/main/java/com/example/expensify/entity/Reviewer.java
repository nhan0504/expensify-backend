package com.example.expensify.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reviewers")
public class Reviewer extends ExpensifyUser {
  @Id @GeneratedValue private Long id;

  private String username;

  private String password;

  private Role role;

  protected Reviewer() {}

  public Reviewer(String username, String password, Role role) {
    super(username, password, role);
    this.username = username;
    this.password = password;
    this.role = role;
  }

  public Long getId() {
    return this.id;
  }
}
