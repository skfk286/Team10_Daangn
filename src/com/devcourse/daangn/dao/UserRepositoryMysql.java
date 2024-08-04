package com.devcourse.daangn.dao;

import com.devcourse.daangn.entity.UserDTO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepositoryMysql implements UserRepository{
    private UserRepositoryMysql() {}

    private static UserRepository instance = new UserRepositoryMysql();

    public static UserRepository getInstance() { return instance; }

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public int saveUser(UserDTO userDTO) throws IOException {
        /* TODO : 사용자 정보 등록(회원가입) */
        return 0;
    }

    @Override
    public UserDTO findUserByName(String userName) throws IOException {
        /* TODO : 닉네임으로 사용자 정보 조회 */
        return null;
    }
}
