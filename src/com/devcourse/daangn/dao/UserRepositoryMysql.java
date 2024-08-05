package com.devcourse.daangn.dao;

import com.devcourse.daangn.entity.UserDTO;
import com.devcourse.daangn.util.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryMysql implements UserRepository{
//    public static void main(String[] args) {
//        UserRepository repo = new UserRepositoryMysql();
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUserName("민성훈");
//        userDTO.setLocation("김천시 다수동");
//        try {
//            // 유저 등록
//            repo.saveUser(userDTO);
//            // 유저 조회
//            System.out.println(repo.findUserByName("민성훈"));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    private UserRepositoryMysql() {}

    private static UserRepository instance = new UserRepositoryMysql();

    public static UserRepository getInstance() { return instance; }

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public int saveUser(UserDTO userDTO) throws SQLException {
        /* TODO : 사용자 정보 등록(회원가입) */
        int result = 0;
        try{
            String sql = " INSERT INTO " +
                    " tb_user(user_name, location) VALUES(?,?) ";
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, userDTO.getUserName());
            ps.setString(2, userDTO.getLocation());
            result = ps.executeUpdate();
        }catch (SQLException ex){
            System.out.println("insert error");
            throw ex;
        }finally {
            DBUtil.close(conn, ps, rs);
        }
        return result;
    }

    @Override
    public UserDTO findUserByName(String userName) throws SQLException {
        /* TODO : 사용자 정보 조회 */
        UserDTO user = null;

        try {
            String sql = " SELECT user_id, user_name, location, created_at FROM tb_user WHERE user_name like '" + userName +"'";
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){ // 글번호 이상하면 없을수는 있음.
                user = makeUserDTO(rs);
            }
        }catch (SQLException ex){
            System.out.println("update error");
            throw ex;
        }finally {
            DBUtil.close(rs, ps, conn);
        }

        return user;
    }
    private UserDTO makeUserDTO(ResultSet rs) throws SQLException {
        UserDTO user = new UserDTO();
        user.setUserName(rs.getString("user_name"));
        user.setLocation(rs.getString("location"));
        return user;
    }
}
