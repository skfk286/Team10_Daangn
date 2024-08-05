package com.devcourse.daangn.dao;

import com.devcourse.daangn.entity.ReviewDTO;
import com.devcourse.daangn.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewRepositoryMysql implements ReviewRepository {

    private static ReviewRepository instance;

    public ReviewRepositoryMysql() {}

    public static ReviewRepository getInstance() {
        if (instance == null) {
        instance = new ReviewRepositoryMysql();
        }
        return instance;
    }


    @Override
    public void createReview(ReviewDTO reviewDTO) {
        String sql = "INSERT INTO review (product_id, user_id, comment, rating, created_at, updated_at, review_type) VALUES (?, ?, ?, ?, NOW(), NOW(), ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, reviewDTO.getProductId());
            ps.setInt(2, reviewDTO.getUserId());
            ps.setString(3, reviewDTO.getComment());
            ps.setInt(4, reviewDTO.getRating());
            ps.setString(5, reviewDTO.getReviewType());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ReviewDTO getReview(int reviewId) {
        String sql = "SELECT * FROM review WHERE review_id = ?";
        ReviewDTO reviewDTO = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, reviewId);
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reviewDTO;
    }

    @Override
    public void updateReview(ReviewDTO reviewDTO) {

    }

    @Override
    public void deleteReview(int reviewId) {
        String sql = "DELETE FROM review WHERE review_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, reviewId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
