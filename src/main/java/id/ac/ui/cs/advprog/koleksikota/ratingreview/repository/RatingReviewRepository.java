package id.ac.ui.cs.advprog.koleksikota.ratingreview.repository;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.RatingReview;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class RatingReviewRepository {
    private final List<RatingReview> ratingReviewData = new ArrayList<>();

    public RatingReview save (RatingReview ratingReview) {
        int i = 0;
        for (RatingReview savedReview : ratingReviewData) {
            if (savedReview.getRatingReviewId().equals(ratingReview.getRatingReviewId())) {
                ratingReviewData.remove(i);
                ratingReviewData.add(i, ratingReview);
                return ratingReview;
            }
            i++;
        }
        ratingReviewData.add(ratingReview);
        return ratingReview;
    }

    public RatingReview findById (String id) {
        for (RatingReview savedReview : ratingReviewData) {
            if (savedReview.getRatingReviewId().equals(id)) {
                return savedReview;
            }
        }
        return null;
    }

    public Iterator<RatingReview> findAll(){
        return ratingReviewData.iterator();
    }

    public boolean delete(String id) {
        for (RatingReview savedReview : ratingReviewData) {
            if (savedReview.getRatingReviewId().equals(id)) {
                ratingReviewData.remove(savedReview);
                return true;
            }
        }
        return false;
    }
}