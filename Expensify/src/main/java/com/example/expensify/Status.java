package com.example.Expensify;

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

    private String reviewed_by;

    private String review_date;

    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getReviewed_by() {
        return reviewed_by;
    }

    public void setReviewed_by(String reviewed_by) {
        this.reviewed_by = reviewed_by;
    }

    public String getReview_date() {
        return review_date;
    }

    public void setReview_date(String review_date) {
        this.review_date = review_date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
