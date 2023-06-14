package com.example.expensify.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "statuses")
public class Status {

  @JsonIgnore @Id @GeneratedValue private Long id;

  @Enumerated(EnumType.STRING)
  private State state;

  @JsonProperty("reviewed_by")
  private String reviewedBy;

  @JsonProperty("review_date")
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

  public void changeTo(Status status) {
    this.state = status.getState();
    this.reviewedBy = status.getReviewedBy();
    this.reviewDate = status.getReviewDate();
    this.comment = status.getComment();
  }

  public static Builder builder() {
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
