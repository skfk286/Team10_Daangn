package com.devcourse.daangn.entity;

public class ReviewDTO extends BaseDTO {
    private int reviewId;
    private int productId;
    private int userId;
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

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "\t---------" +
                "\n\t| 유저 아이디: " + userId +
                "\n\t| 유저 유형: " + reviewType +
                "\n\t---------" +
                "\n\t| 상품 아이디 : " + productId +
                "\n\t---------" +
                "\n\t| 설명 :" +
                "\n\t| " + comment +
                "\n\t---------" +
                "\n\t| 별점: " + rating +
                "\n\t---------" +
                "\n\t| 리뷰 생성일 - " + getCreatedAt() +
                "\n\t---------" +
                "\n";
        }

}




