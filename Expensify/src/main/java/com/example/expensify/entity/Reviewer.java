package com.example.expensify.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reviewers")
public class Reviewer extends ExpensifyUser {

  protected Reviewer() {}

  public Reviewer(String username, String password, Role role) {
    super(username, password, role);
  }
}
