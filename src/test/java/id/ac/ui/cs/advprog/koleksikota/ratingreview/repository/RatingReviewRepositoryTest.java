package id.ac.ui.cs.advprog.koleksikota.ratingreview.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.RatingReview;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RatingReviewRepositoryTest {
    @InjectMocks
    private RatingReviewRepository ratingReviewRepository;
    private List<RatingReview> ratingReviewList;

    @BeforeEach
    void setup(){
        ratingReviewRepository = new RatingReviewRepository();
        ratingReviewList = new ArrayList<>();

        String userId1 = "07f9b8b0-7257-4434-a5b9-79c9703f0760";
        int rating1 = 5;
        String review1 = "Mantap Mang";

        RatingReview ratingReview1 = new RatingReview(userId1, review1, rating1);
        ratingReview1.setRatingReviewId("4ec1f12c-8e2b-4f02-85c0-531e762b483b");
        ratingReviewList.add(ratingReview1);

        String userId2 = "07f9b8b0-7257-4434-a5b9-79c9703f0760";
        int rating2 = 1;
        String review2 = "Kgk enak kiriman lu cok";

        RatingReview ratingReview2 = new RatingReview(userId2, review2, rating2);
        ratingReview2.setRatingReviewId("8d3e2b3b-b0d9-4e80-a641-2e02c1a935fb");
        ratingReviewList.add(ratingReview2);
    }

    @Test
    void testSaveCreateReview(){
        RatingReview ratingReview = ratingReviewList.getFirst();
        RatingReview result = ratingReviewRepository.save(ratingReview);

        RatingReview found = ratingReviewRepository.findById(ratingReviewList.getFirst().getRatingReviewId());
        assertEquals(ratingReview.getRatingReviewId(), result.getRatingReviewId());
        assertEquals(ratingReview.getRatingReviewId(), found.getRatingReviewId());
        assertEquals(ratingReview.getReviewer(), found.getReviewer());
        assertEquals(ratingReview.getReview(), found.getReview());
        assertEquals(ratingReview.getRating(), found.getRating());
    }

    @Test
    void testFindByIdIfIdFound() {
        for (RatingReview review : ratingReviewList) {
            ratingReviewRepository.save(review);
        }

        RatingReview found = ratingReviewRepository.findById(ratingReviewList.getFirst().getRatingReviewId());
        assertEquals(ratingReviewList.getFirst().getRatingReviewId(), found.getRatingReviewId());
        assertEquals(ratingReviewList.getFirst().getReviewer(), found.getReviewer());
        assertEquals(ratingReviewList.getFirst().getReview(), found.getReview());
        assertEquals(ratingReviewList.getFirst().getRating(), found.getRating());
    }

    @Test
    void testFindReviewByIdNotFound() {
        for (RatingReview review : ratingReviewList) {
            ratingReviewRepository.save(review);
        }

        RatingReview findResult = ratingReviewRepository.findById("apaajadahsabeb");
        assertNull(findResult);
    }

    @Test
    void testDeleteReview() {
        for (RatingReview review : ratingReviewList) {
            ratingReviewRepository.save(review);
        }

        assertTrue(ratingReviewRepository.delete(ratingReviewList.getFirst().getRatingReviewId()));

        RatingReview foundResult = ratingReviewRepository.findById(ratingReviewList.getFirst().getRatingReviewId());
        assertNull(foundResult);
    }
}