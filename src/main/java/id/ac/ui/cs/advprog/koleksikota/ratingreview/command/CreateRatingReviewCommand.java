package id.ac.ui.cs.advprog.koleksikota.ratingreview.command;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.RatingReview;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.repository.RatingReviewRepository;

public class CreateRatingReviewCommand implements RatingReviewCommand{
    private final RatingReview ratingReview;
    private final RatingReviewRepository ratingReviewRepository;

    public CreateRatingReviewCommand(RatingReview ratingReview, RatingReviewRepository ratingReviewRepository) {
        this.ratingReview = ratingReview;
        this.ratingReviewRepository = ratingReviewRepository;
    }

    @Override
    public RatingReview execute() {
        return ratingReviewRepository.save(ratingReview);
    }
}
