package id.ac.ui.cs.advprog.koleksikota.ratingreview.command;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.Box;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.RatingReview;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.repository.RatingReviewRepository;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.command.CreateRatingReviewCommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreateRatingReviewCommandTest {
    private RatingReviewRepository ratingReviewRepository;
    private Box box;

    @BeforeEach
    void setUp() {
        ratingReviewRepository = new RatingReviewRepository();
        box = new Box();
        box.setBoxId("a2c62328-4a37-4664-83c7-f32db8620155");
        box.setBoxName("Lempuyangan");
        box.setBoxDescription("Roti O stasiun lempuyangan");
    }

    @Test
    void testExecuteCreate() {
        RatingReview ratingReview = new RatingReview(box, "jajang", "kgk enak kiriman lu cok", 1);
        CreateRatingReviewCommand createRatingReviewCommand = new CreateRatingReviewCommand(ratingReview, ratingReviewRepository);
        createRatingReviewCommand.execute();

        RatingReview savedRatingReview = ratingReviewRepository.findById(ratingReview.getRatingReviewId());
        assertNotNull(savedRatingReview);
        assertEquals(ratingReview.getReviewer(), savedRatingReview.getReviewer());
        assertEquals(ratingReview.getReview(), savedRatingReview.getReview());
        assertEquals(ratingReview.getRating(), savedRatingReview.getRating());
    }
}
