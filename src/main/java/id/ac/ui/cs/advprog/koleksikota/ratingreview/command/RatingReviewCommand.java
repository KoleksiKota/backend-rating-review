package id.ac.ui.cs.advprog.koleksikota.ratingreview.command;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.RatingReview;

import java.util.Optional;

public interface RatingReviewCommand {
    Optional<RatingReview> execute();
}
