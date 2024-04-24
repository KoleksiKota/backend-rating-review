package id.ac.ui.cs.advprog.koleksikota.ratingreview.model.builder;

import java.util.UUID;

public interface Builder {
    public RatingReviewBuilder setReviewId();
    public RatingReviewBuilder setReviewer(String reviewerUsername);
    public RatingReviewBuilder setRating(int rating);
    public RatingReviewBuilder setReview(String review);
    public RatingReviewBuilder setApprovalStatus(String approvalStatus);
}
