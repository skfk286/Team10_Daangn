package com.devcourse.daangn.entity;

public class ReviewDTO extends BaseDTO {
    private int reviewId;
    private int product;
    private int user;
    private String comment;
    private int rating;
    private String reviewType;

    // Getters and Setters
    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewType() {
        return reviewType;
    }

    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }

    public String toString() {
        return "ReviewDTO{" +
                "reviewId=" + reviewId +
                ", product=" + product +
                ", user=" + user +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                ", reviewType='" + reviewType + '\'' +
                ", createdAt=" + getCreatedAt() +
                ", updatedAt=" + getUpdatedAt() +
                '}';
    }


}
