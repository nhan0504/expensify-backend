package com.example.expensify.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "statuses")
public class Status {
  @Id @GeneratedValue private Long id;

  private State state;

  private String reviewedBy;

  private LocalDate reviewDate;

  private String comment;

  protected Status() {}

  private Status(Builder builder) {
    this.state = builder.state;
    this.reviewedBy = builder.reviewedBy;
    this.reviewDate = builder.reviewDate;
    this.comment = builder.comment;
  }

  public Long getId() {
    return this.id;
  }

  public State getState() {
    return this.state;
  }

  public String getReviewedBy() {
    return this.reviewedBy;
  }

  public LocalDate getReviewDate() {
    return this.reviewDate;
  }

  public String getComment() {
    return this.comment;
  }

  public Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private State state;

    private String reviewedBy;

    private LocalDate reviewDate;

    private String comment;

    public Builder state(State state) {
      this.state = state;
      return this;
    }

    public Builder reviewedBy(String reviewedBy) {
      this.reviewedBy = reviewedBy;
      return this;
    }

    public Builder reviewDate(LocalDate reviewDate) {
      this.reviewDate = reviewDate;
      return this;
    }

    public Builder comment(String comment) {
      this.comment = comment;
      return this;
    }

    public Status build() {
      return new Status(this);
    }
  }
}
