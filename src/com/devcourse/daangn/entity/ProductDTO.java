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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("----------------------------------\n");
        sb.append("| 물품 번호: ").append(productId).append("\n");
        sb.append("| 제목: ").append(title).append("\n");
        sb.append("----------------------------------\n");
        sb.append("| 설명:\n");
        sb.append(content).append("\n");
        sb.append("----------------------------------\n");
        sb.append("| 생성일: ").append(getCreatedAt()).append("\n");
        sb.append("| 수정일: ").append(getUpdatedAt()).append("\n");
        sb.append("----------------------------------\n");
        return sb.toString();
    }
}
