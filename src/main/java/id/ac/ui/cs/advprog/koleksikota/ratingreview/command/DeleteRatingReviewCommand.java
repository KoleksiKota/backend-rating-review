package id.ac.ui.cs.advprog.koleksikota.ratingreview.command;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.RatingReview;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.repository.RatingReviewRepository;

public class DeleteRatingReviewCommand implements RatingReviewCommand {
    private final String ratingReviewId;
    private final RatingReviewRepository ratingReviewRepository;

    public DeleteRatingReviewCommand(String ratingReviewId, RatingReviewRepository ratingReviewRepository) {
        this.ratingReviewId = ratingReviewId;
        this.ratingReviewRepository = ratingReviewRepository;
    }

    @Override
    public RatingReview execute() {
        RatingReview ratingReview = ratingReviewRepository.findById(ratingReviewId);
        if (ratingReview != null) {
            ratingReviewRepository.delete(ratingReview.getRatingReviewId());
        }
        return ratingReview;
    }
}
