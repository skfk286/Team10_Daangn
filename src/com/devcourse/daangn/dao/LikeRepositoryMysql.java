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

    public static void main(String[] args) throws IOException {
        UserRepository userRepository = UserRepositoryMysql.getInstance();
        UserDTO userDTO = userRepository.findUserByName("강아지");

        System.out.println(userDTO.getUserId());

        ProductRepository productRepository = ProductRepositoryMysql.getInstance();
        ProductDTO productDTO = productRepository.findProductByProductId(8);
        System.out.println(productDTO.getProductId());

        LikeRepositoryMysql mysql = new LikeRepositoryMysql();
        mysql.UpdateLike(productDTO,userDTO);
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

    public int UpdateLike(ProductDTO productDTO,UserDTO userDTO) throws IOException {
        if(isExist(productDTO, userDTO)){
            return removeLike(productDTO,userDTO);
        }else {
            return addLike(productDTO,userDTO);
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
            // 등록 성공하면 1
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("좋아요 등록 실패");
        }
    }
    public boolean isExist(ProductDTO productDTO,UserDTO userDTO)throws IOException {
        boolean exist = false;
        String sql = "select count(*) from tb_like where product_id=? and user_id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, productDTO.getProductId());
            ps.setInt(2, userDTO.getUserId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString(1));
                if (rs.getInt(1) > 0){
                    exist = true;
                }
            }
            return exist;
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
            // 삭제 성공하면 0
            return  ps.executeUpdate()==1?0:1;
        } catch (SQLException e) {
            throw new RuntimeException("좋아요 삭제 실패");
        }
    }

}
