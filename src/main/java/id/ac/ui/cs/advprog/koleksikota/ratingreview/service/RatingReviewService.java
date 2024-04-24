package id.ac.ui.cs.advprog.koleksikota.ratingreview.service;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.RatingReview;

public interface RatingReviewService {
    public RatingReview create(String boxId, String userId, int rating, String review);
    public RatingReview findById(String ratingReviewId);
    public RatingReview update(String ratingReviewId, RatingReview updatedRatingReview);
    public RatingReview delete(String ratingReviewId);
}
