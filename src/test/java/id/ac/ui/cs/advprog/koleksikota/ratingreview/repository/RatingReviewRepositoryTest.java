package id.ac.ui.cs.advprog.koleksikota.ratingreview.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.Box;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.RatingReview;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RatingReviewRepositoryTest {
    @Mock
    private RatingReviewRepository ratingReviewRepository;
    private List<RatingReview> ratingReviewList;

    @BeforeEach
    void setup(){
        ratingReviewList = new ArrayList<>();

        List<Box> boxes = new ArrayList<>();
        Box box1 = new Box();
        box1.setBoxId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        box1.setBoxName("Jakarta");
        box1.setBoxDescription("Oleh-oleh dari abang none");
        boxes.add(box1);

        Box box = boxes.getFirst();

        RatingReview ratingReview1 = new RatingReview(box, "07f9b8b0-7257-4434-a5b9-79c9703f0760",
                "Mantap Mang", 5);
        ratingReview1.setRatingReviewId("4ec1f12c-8e2b-4f02-85c0-531e762b483b");
        ratingReviewList.add(ratingReview1);

        RatingReview ratingReview2 = new RatingReview(box, "07f9b8b0-7257-4434-a5b9-79c9703f0760",
                "Kgk enak kiriman lu cok", 1);
        ratingReview2.setRatingReviewId("8d3e2b3b-b0d9-4e80-a641-2e02c1a935fb");
        ratingReviewList.add(ratingReview2);
    }

    @Test
    void testSaveCreateReview(){
        RatingReview ratingReview = ratingReviewList.get(0);
        Mockito.when(ratingReviewRepository.save(ratingReview)).thenReturn(ratingReview);
        Mockito.when(ratingReviewRepository.findById(ratingReview.getRatingReviewId())).thenReturn(Optional.of(ratingReview));

        RatingReview result = (RatingReview) ratingReviewRepository.save(ratingReview);

        Optional<RatingReview> findResult = ratingReviewRepository.findById(ratingReview.getRatingReviewId());
        assertEquals(ratingReview.getRatingReviewId(), result.getRatingReviewId());
        assertEquals(ratingReview.getRatingReviewId(), findResult.get().getRatingReviewId());
        assertEquals(ratingReview.getReviewer(), findResult.get().getReviewer());
        assertEquals(ratingReview.getBox(), findResult.get().getBox());
        assertEquals(ratingReview.getReview(), findResult.get().getReview());
        assertEquals(ratingReview.getRating(), findResult.get().getRating());    }

//    @Test
//    void testFindByIdIfIdFound() {
//        for (RatingReview review : ratingReviewList) {
//            ratingReviewRepository.save(review);
//        }
//
//        RatingReview found = ratingReviewRepository.findById(ratingReviewList.getFirst().getRatingReviewId());
//        assertEquals(ratingReviewList.getFirst().getBox(), found.getBox());
//        assertEquals(ratingReviewList.getFirst().getRatingReviewId(), found.getRatingReviewId());
//        assertEquals(ratingReviewList.getFirst().getReviewer(), found.getReviewer());
//        assertEquals(ratingReviewList.getFirst().getReview(), found.getReview());
//        assertEquals(ratingReviewList.getFirst().getRating(), found.getRating());
//    }
//
//    @Test
//    void testFindReviewByIdNotFound() {
//        for (RatingReview review : ratingReviewList) {
//            ratingReviewRepository.save(review);
//        }
//
//        RatingReview foundResult = ratingReviewRepository.findById("apaajadahsabeb");
//        assertNull(foundResult);
//    }
//
//    @Test
//    void testDeleteReview() {
//        for (RatingReview review : ratingReviewList) {
//            ratingReviewRepository.save(review);
//        }
//
//        assertTrue(ratingReviewRepository.delete(ratingReviewList.getFirst().getRatingReviewId()));
//
//        RatingReview foundResult = ratingReviewRepository.findById(ratingReviewList.getFirst().getRatingReviewId());
//        assertNull(foundResult);
//    }
}