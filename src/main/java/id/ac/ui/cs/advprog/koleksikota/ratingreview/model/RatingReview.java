package id.ac.ui.cs.advprog.koleksikota.ratingreview.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.Box;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.enums.ApprovalStatus;

@Getter @Setter
public class RatingReview {
    private Box box;
    private String ratingReviewId;
    private String reviewer;
    private int rating;
    private String review;
    private String approvalStatus;

    public RatingReview(Box box, String reviewer, String review, int rating) {
        this.box = box;
        this.ratingReviewId = UUID.randomUUID().toString();
        this.reviewer = reviewer;
        this.review = review;
        setRating(rating);
        this.approvalStatus = ApprovalStatus.PENDING.getValue();
    }

    public void setRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating tidak valid!");
        }
        this.rating = rating;
    }
}