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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rating-review")
public class RatingReview {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id @Column(name = "id_rating_review", updatable = false, nullable = false)
    private UUID ratingReviewId;

    @Column(name = "reviewer")
    private String reviewer;

    @Column(name = "rating")
    int rating;

    @Column(name = "review")
    String review;

    @Column(name = "approval_status") @Builder.Default
    String approvalStatus = ApprovalStatus.PENDING.getValue();

    @Transient
    Box box;

    public RatingReview(Box box, String reviewer, String review, int rating) {
        this.box = box;
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