package id.ac.ui.cs.advprog.koleksikota.ratingreview.command;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.RatingReview;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.repository.RatingReviewRepository;

import java.util.Optional;

public class EditRatingReviewCommand implements RatingReviewCommand{
    private final RatingReviewRepository ratingReviewRepository;
    private final RatingReview updatedReview;

    public EditRatingReviewCommand(RatingReviewRepository ratingReviewRepository, RatingReview updatedReview) {
        this.ratingReviewRepository = ratingReviewRepository;
        this.updatedReview = updatedReview;
    }

    @Override
    public Optional<RatingReview> execute() {
        Optional<RatingReview> existingRatingReview = ratingReviewRepository.findById(updatedReview.getRatingReviewId());
        if (existingRatingReview.isPresent()) {
            ratingReviewRepository.save(updatedReview);
        }
        return existingRatingReview;
    }
}
