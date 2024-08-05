package com.devcourse.daangn.entity;

public class UserDTO extends BaseDTO{
    private String userName;
    private String location;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String toString(){
        return "UserDTO {" +
                "user_nickname=" + userName +
                ", location=" + location +
                ", createdAt=" + getCreatedAt() +
                ", updatedAt=" + getUpdatedAt() +
                "}";
    }
}
