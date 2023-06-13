package com.example.expensify.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "expenses")
public class Expense {

  @Id @GeneratedValue private Long id;

  private String merchant;

  private String description;

  @JsonProperty("purchase_date")
  private LocalDate purchaseDate;

  private double amount;

  @OneToOne(cascade = CascadeType.ALL)
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

  public void setStatus(Status status) {
    this.status.setState(status.getState());
    this.status.setReviewedBy(status.getReviewedBy());
    this.status.setReviewDate(status.getReviewDate());
    this.status.setComment(status.getComment());
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private String merchant;

    private String description;

    private LocalDate purchaseDate;

    private double amount;

    private Status status;

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
      return new Expense(this);
    }
  }
}
