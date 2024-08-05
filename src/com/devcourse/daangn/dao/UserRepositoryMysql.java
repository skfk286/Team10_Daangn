package com.devcourse.daangn.dao;

import com.devcourse.daangn.entity.UserDTO;
import com.devcourse.daangn.util.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryMysql implements UserRepository{
    //단위 테스트
//    public static void main(String[] args) {
//        UserRepository repo = new UserRepositoryMysql();
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUserName("민성훈");
//        userDTO.setLocation("김천시 다수동");
//        try {
//             유저 등록
//            repo.saveUser(userDTO);
//             유저 조회
//            System.out.println(repo.findUserByName("민성훈"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
    private UserRepositoryMysql() {}

    private static final UserRepository instance = new UserRepositoryMysql();

    public static UserRepository getInstance() { return instance; }

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public int saveUser(UserDTO userDTO) throws IOException {
        /* TODO : 사용자 정보 등록(회원가입) */
        int result = 0;
        String sql = " INSERT INTO " +
                " tb_user(user_name, location) VALUES(?,?) ";
        conn = DBUtil.getConnection();
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, userDTO.getUserName());
            ps.setString(2, userDTO.getLocation());
            result = ps.executeUpdate();
            return result;
        }catch (SQLException ex){
            throw new IOException("Insert Error");
        }
    }

    @Override
    public UserDTO findUserByName(String userName) throws IOException {
        /* TODO : 사용자 정보 조회 */
        UserDTO user = null;
        String sql = " SELECT user_id, user_name, location, created_at FROM tb_user WHERE user_name like '" + userName +"'";
        conn = DBUtil.getConnection();
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            rs = ps.executeQuery();
            if(rs.next()){
                user = makeUserDTO(rs);
            }
            return user;
        }catch (SQLException ex){
            throw new IOException("Update Error");
        }
    }

//    public int deleteById(int productId) throws IOException {
//        String sql = "DELETE FROM daangn.tb_product WHERE product_id = ?";
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setInt(1, productId);
//
//            return ps.executeUpdate();
//        } catch (SQLException e) {
//            throw new IOException("Error deleting product", e);
//        }
//    }


    private UserDTO makeUserDTO(ResultSet rs) throws SQLException {
        UserDTO user = new UserDTO();
        user.setUserName(rs.getString("user_name"));
        user.setLocation(rs.getString("location"));
        return user;
    }
}
