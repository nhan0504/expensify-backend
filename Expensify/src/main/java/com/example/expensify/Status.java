package com.example.expensify;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "statuses")
public class Status {
    @Id
    @GeneratedValue
    private Long id;

    private State state;

    private String reviewedBy;

    private String reviewDate;

    private String comment;

    public Status() {}

    public Status(State state, String reviewedBy, String reviewDate, String comment) {
        this.state = state;
        this.reviewedBy = reviewedBy;
        this.reviewDate = reviewDate;
        this.comment = comment;
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

    public String getReviewDate() {
        return this.reviewDate;
    }

    public String getComment() {
        return this.comment;
    }

}
