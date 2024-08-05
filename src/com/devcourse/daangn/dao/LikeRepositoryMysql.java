package com.devcourse.daangn.dao;

import com.devcourse.daangn.entity.ProductDTO;
import com.devcourse.daangn.entity.UserDTO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class LikeRepositoryMysql implements LikeRepository {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private static LikeRepositoryMysql instance = new LikeRepositoryMysql();

    public static LikeRepositoryMysql getInstance() {
        return instance;
    }

    @Override
    public int findByProduct(ProductDTO productDTO) throws IOException {
        return 0;
    }

    @Override
    public int addLike(ProductDTO productDTO,UserDTO userDTO) throws IOException {
        /* TODO : 좋아요 등록 */
        int result = 0;
        String sql = "insert into likes (product_id, user_id) values (?, ?)";
        return result;
    }

    @Override
    public int removeLike(ProductDTO productDTO) throws IOException {
        /* TODO : 좋아요 삭제 */
        int result = 0;
        String sql = "delete from likes where product_id = ? && user_id = ?";
        return 0;
    }

    @Override
    public List<ProductDTO> findByUserId(UserDTO userDTO) throws IOException {
        /* TODO : 사용자가 좋아요를 누른 모든 상품 목록 조회 */
        return List.of();
    }
}
