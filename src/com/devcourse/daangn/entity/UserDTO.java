package com.devcourse.daangn.entity;

public class UserDTO extends BaseDTO{
    private String user_nickname;
    private String location;

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String toString(){
        return "UserDTO {" +
                "user_nickname=" + user_nickname +
                ", location=" + location +
                ", createdAt=" + getCreatedAt() +
                ", updatedAt=" + getUpdatedAt() +
                "}";
    }
}
