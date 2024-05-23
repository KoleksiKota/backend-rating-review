package id.ac.ui.cs.advprog.koleksikota.ratingreview.command;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.RatingReview;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.repository.RatingReviewRepository;

import java.util.Optional;
import java.util.UUID;

public class DeleteRatingReviewCommand implements RatingReviewCommand {
    private final UUID ratingReviewId;
    private final RatingReviewRepository ratingReviewRepository;

    public DeleteRatingReviewCommand(UUID ratingReviewId, RatingReviewRepository ratingReviewRepository) {
        this.ratingReviewId = ratingReviewId;
        this.ratingReviewRepository = ratingReviewRepository;
    }

    @Override
    public Optional<RatingReview> execute() {
        Optional<RatingReview> ratingReview = ratingReviewRepository.findById(String.valueOf(ratingReviewId));
        ratingReview.ifPresent(value -> ratingReviewRepository.deleteById(String.valueOf(value)));
        return ratingReview;
    }
}
