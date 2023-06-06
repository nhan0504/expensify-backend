package com.example.expensify.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "reviewers")
public class Reviewer extends ExpensifyUser {
  @Id @GeneratedValue private Long id;

  private String username;

  private String password;

  private Role role;

  protected Reviewer() {}

  public Reviewer(String username, String password, Role role) {
    this.username = username;
    this.password = password;
    this.role = role;
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
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singleton(new SimpleGrantedAuthority(this.role.toString()));
  }
}