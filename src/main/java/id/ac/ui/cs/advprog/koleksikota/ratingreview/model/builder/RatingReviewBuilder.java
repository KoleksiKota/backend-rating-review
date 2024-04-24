package id.ac.ui.cs.advprog.koleksikota.ratingreview.model.builder;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.RatingReview;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RatingReviewBuilder implements Builder {
    private RatingReview ratingReview;

    public RatingReviewBuilder() {
        ratingReview = new RatingReview();
    }

    public RatingReview build() {
        return this.ratingReview;
    }

    public RatingReviewBuilder reset() {
        ratingReview = new RatingReview();
        return this;
    }

    public RatingReviewBuilder setInstance(RatingReview RatingReview){
        this.ratingReview = RatingReview;
        return this;
    }

    public RatingReviewBuilder setRatingReviewId() {
        ratingReview.setRatingReviewId(UUID.randomUUID());
        return this;
    }

    public RatingReviewBuilder setReviewer(String reviewerUsername) {
        ratingReview.setReviewer(reviewerUsername);
        return this;
    }

    public RatingReviewBuilder setRating(int rating) {
        ratingReview.setRating(rating);
        return this;
    }

    public RatingReviewBuilder setReview(String review) {
        ratingReview.setReview(review);
        return this;
    }

    public RatingReviewBuilder setApprovalStatus(String approvalStatus) {
        ratingReview.setApprovalStatus(approvalStatus);
        return this;
    }
}
