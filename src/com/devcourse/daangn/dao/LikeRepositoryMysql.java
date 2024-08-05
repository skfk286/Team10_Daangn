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

    private static final LikeRepositoryMysql instance = new LikeRepositoryMysql();

    public static LikeRepositoryMysql getInstance() {
        return instance;
    }


    @Override
    public int findByProduct(ProductDTO productDTO) throws IOException {
        int result = 0;
        String sql = "select count(*) from tb_like where product_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, productDTO.getProductId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
            return result;
        }catch (SQLException e){
            throw new RuntimeException("좋아요 조회 실패");
        }
    }

    @Override
    public int addLike(ProductDTO productDTO,UserDTO userDTO)throws IOException {
        /* TODO : 좋아요 등록 */
        String sql = "insert into tb_like (product_id, user_id) values (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, productDTO.getProductId());
            ps.setInt(2, userDTO.getUserId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("좋아요 등록 실패");
        }
    }

    @Override
    public int removeLike(ProductDTO productDTO, UserDTO userDTO) throws IOException {
        /* TODO : 좋아요 삭제 */

        String sql = "delete from tb_like where product_id = ? && user_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, productDTO.getProductId());
            ps.setInt(2, userDTO.getUserId());
            return  ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("좋아요 삭제 실패");
        }
    }

}
