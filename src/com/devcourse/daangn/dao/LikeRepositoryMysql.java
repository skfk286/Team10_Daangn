package com.devcourse.daangn.dao;

import com.devcourse.daangn.entity.ProductDTO;
import com.devcourse.daangn.entity.UserDTO;
import com.devcourse.daangn.util.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikeRepositoryMysql implements LikeRepository {
    private Connection conn;
    private PreparedStatement pstms;
    private ResultSet rs;
    private static LikeRepositoryMysql instance = new LikeRepositoryMysql();

    public static LikeRepositoryMysql getInstance() {
        return instance;
    }


    @Override
    public int findByProduct(ProductDTO productDTO) throws IOException {
        int result = 0;
        try {
            String sql = "select count(*) from like where product_id=?";
            pstms = conn.prepareStatement(sql);
            pstms.setInt(1, productDTO.getProductId());
            rs = pstms.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }

        }catch (SQLException e){
            System.out.println("좋아요 조회 실패");
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int addLike(ProductDTO productDTO,UserDTO userDTO)throws IOException {
        /* TODO : 좋아요 등록 */
        int result = 0;
        try {
            String sql = "insert into likes (product_id, user_id) values (?, ?)";
            conn = DBUtil.getConnection();
            pstms = conn.prepareStatement(sql);
            pstms.setInt(1, productDTO.getProductId());
            pstms.setInt(2, userDTO.getUserId());
            result = pstms.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int removeLike(ProductDTO productDTO, UserDTO userDTO) throws IOException {
        /* TODO : 좋아요 삭제 */

        int result = 0;
        try {
            String sql = "delete from likes where product_id = ? && user_id = ?";
            conn = DBUtil.getConnection();
            pstms = conn.prepareStatement(sql);
            pstms.setInt(1, productDTO.getProductId());
            pstms.setInt(2, userDTO.getUserId());
            result = pstms.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

//    @Override
//    public List<ProductDTO> findByUser(UserDTO userDTO) throws IOException {
//        return List.of();
//    }
}
