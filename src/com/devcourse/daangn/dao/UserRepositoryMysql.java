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
////             유저 등록
//            repo.saveUser(userDTO);
////             유저 조회
////            System.out.println(repo.findUserByName("민성훈"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
    private UserRepositoryMysql() {}

    private static UserRepository instance = new UserRepositoryMysql();
    public static UserRepository getInstance() { return instance; }

    @Override
    public int saveUser(UserDTO userDTO) throws IOException {
        /* TODO : 사용자 정보 등록(회원가입) */
        int result = 0;

        //true -> 정보 등록, false -> 정보 등록 실패
        if(!userDuplicateCheck(userDTO)){
            throw new RuntimeException("중복");
        }

        String sql = " INSERT INTO " +
                " tb_user(user_name, location) VALUES(?,?) ";
        try(Connection conn = DBUtil.getConnection()){
            try(PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, userDTO.getUserName());
                ps.setString(2, userDTO.getLocation());
                result = ps.executeUpdate();
                return result;
            }
        }catch (SQLException ex){
            throw new IOException("Insert Error");
        }
    }

    public boolean userDuplicateCheck(UserDTO userDTO) throws IOException {

        boolean result = false;
        String sql = " SELECT COUNT(*) FROM tb_user WHERE user_name = '" + userDTO.getUserName() + "'";

        try(Connection conn = DBUtil.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            try(ResultSet rs = ps.executeQuery()) {
                while(rs.next()){
                    if(rs.getInt(1) == 0){
    //                result가 true일 경우 등록된 이름이 없음으로 실행
    //                false일 경우 등록된 이름이 있음으로 실패
                        result = true;
                    }
                }
                return result;
            }
        }catch (SQLException ex){
            throw new IOException("");
        }
    }

    @Override
    public UserDTO findUserByName(String userName) throws IOException {
        /* TODO : 사용자 정보 조회 */
        UserDTO user = null;
        String sql = " SELECT user_id, user_name, location, created_at FROM tb_user WHERE user_name like '" + userName +"'";
        try(Connection conn = DBUtil.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = makeUserDTO(rs);
                }
                return user;
            }
        }catch (SQLException ex){
            throw new IOException("Update Error");
        }
    }




    private UserDTO makeUserDTO(ResultSet rs) throws SQLException {
        UserDTO user = new UserDTO();
        user.setUserId(rs.getInt("user_id"));
        user.setUserName(rs.getString("user_name"));
        user.setLocation(rs.getString("location"));
        return user;
    }
}
