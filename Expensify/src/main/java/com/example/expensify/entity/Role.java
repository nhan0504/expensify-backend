package com.example.expensify.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
  @Id @GeneratedValue private Long id;

  private String role;

  private Long employee_id;

  protected Role() {}

  public Role(Long employee_id, String role) {
    this.employee_id = employee_id;
    this.role = role;
  }

  public Long getId() {
    return this.id;
  }

  @Override
  public String getAuthority() {
    return this.role;
  }

  public Long getEmployee_id() {
    return this.employee_id;
  }
}
