package com.devcourse.daangn.dao;

import com.devcourse.daangn.entity.ReviewDTO;
import com.devcourse.daangn.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReviewRepositoryImpl implements ReviewRepository {

    private static final DBUtil dbUtil = new DBUtil();

    private static ReviewRepository instance;

    private ReviewRepositoryImpl() {}

    public static ReviewRepository getInstance() {
        if (instance == null) {
        instance = new ReviewRepositoryImpl();
        }
        return instance;
    }


    @Override
    public void createReview(ReviewDTO reviewDTO) {

    }

    @Override
    public ReviewDTO getReview(int reviewId) {
        return null;
    }

    @Override
    public void updateReview(ReviewDTO reviewDTO) {

    }

    @Override
    public void deleteReview(int reviewId) {

    }
}
