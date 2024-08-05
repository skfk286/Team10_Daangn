package com.devcourse.daangn.dao;

import com.devcourse.daangn.entity.ReviewDTO;

public interface ReviewRepository {

    void createReview(ReviewDTO reviewDTO);
    ReviewDTO getReview(int reviewId);
    void updateReview(ReviewDTO reviewDTO);
    void deleteReview(int reviewId);


}
