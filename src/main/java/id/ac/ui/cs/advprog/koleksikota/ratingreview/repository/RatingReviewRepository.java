package id.ac.ui.cs.advprog.koleksikota.ratingreview.repository;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.RatingReview;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class RatingReviewRepository {
    private final Map<String, RatingReview> ratingReviewMap = new HashMap<>();

    public RatingReview save(RatingReview RatingReview) {
        ratingReviewMap.put(RatingReview.getRatingReviewId().toString(), RatingReview);
        return RatingReview;
    }

    public RatingReview findById(String ratingReviewId){
        return ratingReviewMap.get(ratingReviewId);
    }

    public Collection<RatingReview> findAll(){
        return ratingReviewMap.values();
    }

    public RatingReview delete(String ratingReviewId) {
        if (ratingReviewMap.containsKey(ratingReviewId)) {
            return ratingReviewMap.remove(ratingReviewId);
        }
        else {
            return null;
        }
    }
}
