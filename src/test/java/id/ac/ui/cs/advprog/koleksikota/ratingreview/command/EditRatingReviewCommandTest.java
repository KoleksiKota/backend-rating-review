package id.ac.ui.cs.advprog.koleksikota.ratingreview.command;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.Box;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.RatingReview;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.repository.RatingReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EditRatingReviewCommandTest {
    private RatingReviewRepository ratingReviewRepository;
    private Box box;

    @BeforeEach
    void setUp() {
        ratingReviewRepository = new RatingReviewRepository();
        box = new Box();
        box.setBoxId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        box.setBoxName("Jakarta");
        box.setBoxDescription("Oleh-oleh dari abang none");
    }

    @Test
    void testExecuteEdit() {
        RatingReview ratingReview = new RatingReview(box, "jajang", "bagus boxnya, ada  bau kali ciliwung", 4);
        ratingReviewRepository.save(ratingReview);

        ratingReview.setRating(3);
        ratingReview.setReview("not bad lah");
        EditRatingReviewCommand editRatingReviewCommand = new EditRatingReviewCommand(ratingReviewRepository, ratingReview);
        editRatingReviewCommand.execute();

        RatingReview updatedRatingReview = ratingReviewRepository.findById(ratingReview.getRatingReviewId());
        assertEquals(3, updatedRatingReview.getRating());
        assertEquals("not bad lah", updatedRatingReview.getReview());
    }
}
