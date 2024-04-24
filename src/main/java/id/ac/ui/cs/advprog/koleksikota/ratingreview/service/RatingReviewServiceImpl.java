package id.ac.ui.cs.advprog.koleksikota.ratingreview.service;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.RatingReview;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.builder.RatingReviewBuilder;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.repository.RatingReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingReviewServiceImpl implements RatingReviewService {
    private final RatingReviewRepository ratingReviewRepository;
    private final RatingReviewBuilder reviewBuilder = new RatingReviewBuilder();

    @Autowired
    public RatingReviewServiceImpl(RatingReviewRepository ratingReviewRepository) {
        this.ratingReviewRepository = ratingReviewRepository;
    }

    public RatingReview create(String boxId, String userId, int rating, String review) {

        return null;
    }
    public RatingReview findById(String reviewId){
        return null;
    }
    public RatingReview update(String reviewId, RatingReview updatedRatingReview){
        return null;
    }
    public RatingReview delete(String reviewId){
        return null;
    }
}
