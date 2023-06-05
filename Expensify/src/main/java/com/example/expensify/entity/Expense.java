package com.example.expensify.entity;

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

  protected Expense() {}

  public Expense(Builder builder) {
    this.merchant = builder.merchant;
    this.description = builder.description;
    this.purchaseDate = builder.purchaseDate;
    this.amount = builder.amount;
    this.status = builder.status;
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

  public double getAmount() {
    return this.amount;
  }

  public Status getStatus() {
    return this.status;
  }

  public Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String merchant;

    private String description;

    private LocalDate purchaseDate;

    private double amount;

    private Status status;

    public Builder Builder() {
      this.merchant = "";
      this.description = "";
      this.purchaseDate = LocalDate.now();
      this.amount = 0;
      this.status = null;
      return this;
    }

    public void merchant(String merchant) {
      this.merchant = merchant;
    }

    public void description(String description) {
      this.description = description;
    }

    public void purchaseDate(LocalDate purchaseDate) {
      this.purchaseDate = purchaseDate;
    }

    public void amount(double amount) {
      this.amount = amount;
    }

    public void status(Status status) {
      this.status = status;
    }

    public Expense build() {
      if (this.merchant.equals("")) {
        throw new RuntimeException("Merchant name cannot be empty");
      }
      if (this.status == null) {
        throw new RuntimeException("Have to set status");
      }
      return new Expense(this);
    }
  }
}
