package id.ac.ui.cs.advprog.koleksikota.ratingreview.service;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.command.RatingReviewCommand;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.RatingReview;

import java.util.List;
import java.util.Optional;

public interface RatingReviewService {
    Optional<RatingReview> executeCommand(RatingReviewCommand command);
    List<RatingReview> findAll();
    RatingReview findRatingReviewByBox(String boxId);
}
