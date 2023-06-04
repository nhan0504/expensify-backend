package com.example.expensify;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "expenses")
public class Expense {
  @Id @GeneratedValue private Long id;

  private String merchant;

  private String description;

  private LocalDate purchaseDate;

  private double amount;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "status_id")
  private Status status;

  public Expense() {}

  public Expense(
      String merchant, String description, LocalDate purchaseDate, double amount, Status status) {
    this.merchant = merchant;
    this.description = description;
    this.purchaseDate = purchaseDate;
    this.amount = amount;
    this.status = status;
  }

  public Long getId() {
    return this.id;
  }

  public String getMerchant() {
    return this.merchant;
  }

  public String getDescription() {
    return this.description;
  }

  public LocalDate getPurchaseDate() {
    return this.purchaseDate;
  }

  public Status getStatus() {
    return this.status;
  }
}
