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

  @OneToOne
  @JoinColumn(name = "status_id")
  private Status status;

  protected Expense() {}

  private Expense(Builder builder) {
    this.merchant = builder.merchant;
    this.description = builder.description;
    this.purchaseDate = builder.purchaseDate;
    this.amount = builder.amount;
    this.status = builder.status;
  }

  private Expense(Long id, Builder builder) {
    this.merchant = builder.merchant;
    this.description = builder.description;
    this.purchaseDate = builder.purchaseDate;
    this.amount = builder.amount;
    this.status = builder.status;
    this.id = id;
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

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private Long id;

    private String merchant;

    private String description;

    private LocalDate purchaseDate;

    private double amount;

    private Status status;

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Builder merchant(String merchant) {
      this.merchant = merchant;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder purchaseDate(LocalDate purchaseDate) {
      this.purchaseDate = purchaseDate;
      return this;
    }

    public Builder amount(double amount) {
      this.amount = amount;
      return this;
    }

    public Builder status(Status status) {
      this.status = status;
      return this;
    }

    public Expense build() {
      if (this.id != null){
        return new Expense(id, this);
      }
      return new Expense(this);
    }
  }
}
