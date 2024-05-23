package id.ac.ui.cs.advprog.koleksikota.ratingreview.command;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.Box;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.RatingReview;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.repository.RatingReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EditRatingReviewCommandTest {

    @Mock
    private RatingReviewRepository ratingReviewRepository;

    private RatingReview ratingReview;
    private Box box;

    @BeforeEach
    void setUp() {
        box = new Box();
        box.setBoxId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        box.setBoxName("Jakarta");
        box.setBoxDescription("Oleh-oleh dari abang none");

        ratingReview = new RatingReview(box, "jajang", "bagus boxnya, ada bau kali ciliwung", 4);
    }

    @Test
    void testExecuteEdit() {
        when(ratingReviewRepository.save(any(RatingReview.class))).thenReturn(ratingReview);

        when(ratingReviewRepository.findById(String.valueOf(ratingReview.getRatingReviewId()))).thenReturn(Optional.of(ratingReview));

        ratingReview.setRating(3);
        ratingReview.setReview("not bad lah");
        EditRatingReviewCommand editRatingReviewCommand = new EditRatingReviewCommand(ratingReviewRepository, ratingReview);
        editRatingReviewCommand.execute();

        Optional<RatingReview> updatedRatingReview = ratingReviewRepository.findById(String.valueOf(ratingReview.getRatingReviewId()));
        assertEquals(3, updatedRatingReview.get().getRating()); // Check the new rating
        assertEquals("not bad lah", updatedRatingReview.get().getReview()); // Check the new review
    }
}
