package com.devcourse.daangn.entity;

import java.time.LocalDateTime;
import java.util.List;

public class BaseDTO {

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BaseDTO() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void markUpdated() {
        this.updatedAt = LocalDateTime.now();
    }



    //적당한데 넣어서 써야함
    public static void printList(List<?> list) {
        list.forEach(item -> System.out.println(item.toString()));
    }

}
