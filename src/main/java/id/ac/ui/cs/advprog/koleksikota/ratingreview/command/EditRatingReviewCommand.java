package id.ac.ui.cs.advprog.koleksikota.ratingreview.command;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.RatingReview;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.repository.RatingReviewRepository;

public class EditRatingReviewCommand implements RatingReviewCommand{
    private final RatingReviewRepository ratingReviewRepository;
    private final RatingReview updatedReview;

    public EditRatingReviewCommand(RatingReviewRepository ratingReviewRepository, RatingReview updatedReview) {
        this.ratingReviewRepository = ratingReviewRepository;
        this.updatedReview = updatedReview;
    }

    @Override
    public RatingReview execute() {
        RatingReview existingReview = ratingReviewRepository.findById(updatedReview.getRatingReviewId());
        if (existingReview != null) {
            ratingReviewRepository.save(updatedReview);
        }
        return existingReview;
    }
}
