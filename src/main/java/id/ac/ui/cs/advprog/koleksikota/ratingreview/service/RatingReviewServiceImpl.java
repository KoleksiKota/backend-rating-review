package id.ac.ui.cs.advprog.koleksikota.ratingreview.service;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.RatingReview;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.builder.RatingReviewBuilder;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.repository.RatingReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class RatingReviewServiceImpl implements RatingReviewService {
    @Autowired
    private RatingReviewRepository ratingReviewRepository;
    private final RatingReviewBuilder reviewBuilder = new RatingReviewBuilder();

    @Override
    public RatingReview create(String boxId, String userId, int rating, String review) {
        RatingReview ratingReview = new RatingReview();
        ratingReview.setRatingReviewId(UUID.randomUUID());
        ratingReview.setReviewer(userId);
        ratingReview.setRating(rating);
        ratingReview.setReview(review);
        ratingReviewRepository.save(ratingReview);
        return ratingReview;
    }
    @Override
    public RatingReview findById(String ratingReviewId){
        return ratingReviewRepository.findById(ratingReviewId);
    }

    @Override
    public RatingReview update(String ratingReviewId, RatingReview updatedRatingReview){
        RatingReview ratingReview = ratingReviewRepository.findById(ratingReviewId);
        if (ratingReview != null){
            ratingReviewRepository.save(updatedRatingReview);
            return updatedRatingReview;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public RatingReview delete(String ratingReviewId){
        return ratingReviewRepository.delete(ratingReviewId);
    }
}
