package id.ac.ui.cs.advprog.koleksikota.ratingreview.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class RatingReview {
    private UUID ratingReviewId;
    private String reviewer;
    private int rating;
    private String review;
    private String approvalStatus;
}
