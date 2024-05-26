package id.ac.ui.cs.advprog.koleksikota.ratingreview.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.Box;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.enums.ApprovalStatus;
import org.springframework.stereotype.Controller;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rating-review")
public class RatingReview {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id @Column(name = "id_rating_review", updatable = false, nullable = false)
    private String ratingReviewId;

    @Column(name = "userId")
    private String userId;

    @Column(name = "rating")
    int rating;

    @Column(name = "review")
    String review;

    @Column(name = "approval_status")
    String approvalStatus = ApprovalStatus.PENDING.getValue();

    @Transient
    Box box;

    @Column(name = "subscriptionBoxId")
    String subscriptionBoxId;

    public RatingReview(Box box, String reviewer, String review, int rating) {
        this.box = box;
        this.userId = reviewer;
        this.review = review;
        setRating(rating);
        this.approvalStatus = ApprovalStatus.PENDING.getValue();
    }

    public RatingReview(String boxId, String reviewer, String review, int rating) {
        this.subscriptionBoxId = boxId;
        this.userId = reviewer;
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