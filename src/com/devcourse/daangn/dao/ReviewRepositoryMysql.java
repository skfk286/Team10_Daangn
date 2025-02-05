package com.devcourse.daangn.dao;

import com.devcourse.daangn.entity.ReviewDTO;
import com.devcourse.daangn.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepositoryMysql implements ReviewRepository {


    public ReviewRepositoryMysql() {}

    private static final ReviewRepository instance = new ReviewRepositoryMysql();

    private static ReviewRepository getInstance(){return instance;}



    @Override
    public void createReview(ReviewDTO reviewDTO) {
        String sql = "INSERT INTO tb_review (product_id, user_id, comment, rating, created_at, updated_at, review_type) VALUES (?, ?, ?, ?, NOW(), NOW(), ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, reviewDTO.getProductId());
            ps.setInt(2, reviewDTO.getUserId());
            ps.setString(3, reviewDTO.getComment());
            ps.setInt(4, reviewDTO.getRating());
            ps.setString(5, reviewDTO.getReviewType());

            ps.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            // 중복된 product_id와 user_id 조합으로 인해 발생하는 예외를 처리합니다.
            System.out.println("해당 상품에 대해 이미 후기가 존재합니다. 다른 후기를 작성해 주세요.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ReviewDTO getReview(int reviewId) {
        String sql = "SELECT * FROM tb_review WHERE review_id = ?";
        ReviewDTO reviewDTO = null;


        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {


            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    reviewDTO = new ReviewDTO();
                    reviewDTO.setReviewId(rs.getInt("review_id"));
                    reviewDTO.setProductId(rs.getInt("product_id"));
                    reviewDTO.setUserId(rs.getInt("user_id"));
                    reviewDTO.setComment(rs.getString("comment"));
                    reviewDTO.setRating(rs.getInt("rating"));
                    reviewDTO.setReviewType(rs.getString("review_type"));
                }
                return reviewDTO;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reviewDTO;
    }

    @Override
    public List<ReviewDTO> getReviewsByUserId(int userId) {
        String sql = "SELECT * FROM tb_review WHERE user_id = ?";
        List<ReviewDTO> reviews = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ReviewDTO reviewDTO = new ReviewDTO();
                    reviewDTO.setReviewId(rs.getInt("review_id"));
                    reviewDTO.setProductId(rs.getInt("product_id"));
                    reviewDTO.setUserId(rs.getInt("user_id"));
                    reviewDTO.setComment(rs.getString("comment"));
                    reviewDTO.setRating(rs.getInt("rating"));
                    reviewDTO.setReviewType(rs.getString("review_type"));
                    reviews.add(reviewDTO);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reviews;
    }


    @Override
    public void updateReview(ReviewDTO reviewDTO) {

    }

    @Override
    public void deleteReview(int reviewId) {
        String sql = "DELETE FROM tb_review WHERE review_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, reviewId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
