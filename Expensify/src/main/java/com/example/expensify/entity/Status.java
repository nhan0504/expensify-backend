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

  public Status(Builder builder) {
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

    public Builder Builder() {
      this.state = State.IN_REVIEW;
      this.reviewedBy = "";
      this.reviewDate = LocalDate.now();
      this.comment = "";
      return this;
    }

    public void state(State state) {
      this.state = state;
    }

    public void reviewedBy(String reviewedBy) {
      this.reviewedBy = reviewedBy;
    }

    public void reviewDate(LocalDate reviewDate) {
      this.reviewDate = reviewDate;
    }

    public void comment(String comment) {
      this.comment = comment;
    }

    public Status build() {
      if (this.reviewedBy.equals("")) {
        throw new RuntimeException("ReviewBy name cannot be empty");
      }
      return new Status(this);
    }
  }
}
