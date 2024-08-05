package com.devcourse.daangn.entity;

public class ProductDTO extends BaseDTO {

    private int productId;
    private int userId;
    private String title;
    private String content;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", userId='" + userId +
                ", title=" + title +
                ", content=" + content +
                ", createdAt=" + getCreatedAt() +
                ", updatedAt=" + getUpdatedAt() +
                '}';
    }
}
