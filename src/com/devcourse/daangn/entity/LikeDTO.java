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

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    @Override
    public String toString() {
        return "LikeDTO{" +
                "likeId=" + likeId +
                ", userDTO=" + userDTO +
                ", productDTO=" + productDTO +
                '}';
    }
}
