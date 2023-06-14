package com.example.expensify.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@MappedSuperclass
public abstract class ExpensifyUser implements UserDetails {

  @Id @GeneratedValue private Long id;

  private String username;

  @JsonIgnore
  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;

  protected ExpensifyUser() {}

  protected ExpensifyUser(String username, String password, Role role) {
    this.username = username;
    this.password = password;
    this.role = role;
  }

  public Long getId() {
    return this.id;
  }

  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singleton(new SimpleGrantedAuthority(this.role.toString()));
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
  @JsonIgnore
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isEnabled() {
    return true;
  }
}
