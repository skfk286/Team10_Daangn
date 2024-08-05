package com.devcourse.daangn.entity;

public class LikeDTO {
    private int likeId;
    private UserDTO userDTO;
    private ProductDTO productDTO;

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "LikeDTO{" +
                "likeId=" + likeId +
                ", userId=" + userId +
                ", productId=" + productId +
                '}';
    }
}
