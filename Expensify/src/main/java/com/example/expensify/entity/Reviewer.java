package com.example.expensify.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reviewers")
public class Reviewer extends ExpensifyUser {
  @Id @GeneratedValue private Long id;

  protected Reviewer() {}

  public Reviewer(String username, String password, Role role) {
    super(username, password, role);
  }

  public Long getId() {
    return this.id;
  }
}
