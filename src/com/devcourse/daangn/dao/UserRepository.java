package com.devcourse.daangn.dao;

import com.devcourse.daangn.entity.UserDTO;

import java.io.IOException;

public interface UserRepository {
    int saveUser(UserDTO userDTO) throws IOException; // 사용자 정보 등록(회원가입)
    UserDTO findUserByName(String userName) throws IOException; // 사용자 정보 조회
}
