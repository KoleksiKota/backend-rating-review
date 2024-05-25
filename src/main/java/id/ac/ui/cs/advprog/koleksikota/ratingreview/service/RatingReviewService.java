package id.ac.ui.cs.advprog.koleksikota.ratingreview.service;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.command.RatingReviewCommand;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.RatingReview;

import java.util.List;
import java.util.Optional;

public interface RatingReviewService {
    Optional<RatingReview> executeCommand(RatingReviewCommand command);
    List<RatingReview> findAll();
    List<RatingReview> findAllByUserId(String userId);
    List<RatingReview> findAllBySubscriptionBoxId(String subscriptionBoxId);
    Optional<RatingReview> findReviewById(String ReviewId);
    List<RatingReview> findAllByRatingAndSubscriptionBoxId(int rating, String subscriptionBoxId);
    RatingReview updateReviewStatus(String idReview, String status);
    RatingReview findRatingReviewByBox(String productId);
}
