package com.devcourse.daangn.dao;

import com.devcourse.daangn.entity.ReviewDTO;

import java.util.List;

public interface ReviewRepository {

    void createReview(ReviewDTO reviewDTO);
    ReviewDTO getReview(int reviewId);
    List<ReviewDTO> getReviewsByUserId(int userId);
    void updateReview(ReviewDTO reviewDTO);
    void deleteReview(int reviewId);


}
