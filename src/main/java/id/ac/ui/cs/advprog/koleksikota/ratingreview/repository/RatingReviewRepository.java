package id.ac.ui.cs.advprog.koleksikota.ratingreview.repository;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.RatingReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingReviewRepository extends JpaRepository<RatingReview, String> {
    List<RatingReview> findAllByUserId(String userId);
    List<RatingReview> findAllBySubscriptionBoxId(String subscriptionBoxId);
    List<RatingReview> findAllByRatingAndSubscriptionBoxId(int rating, String subscriptionBoxId);
}