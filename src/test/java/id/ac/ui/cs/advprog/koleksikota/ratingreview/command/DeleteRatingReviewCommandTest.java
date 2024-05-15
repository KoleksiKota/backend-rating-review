package id.ac.ui.cs.advprog.koleksikota.ratingreview.command;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.Box;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.RatingReview;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.repository.RatingReviewRepository;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.command.DeleteRatingReviewCommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class DeleteRatingReviewCommandTest {
    @Mock
    private RatingReviewRepository ratingReviewRepository;
    private Box box;

    @BeforeEach
    void setUp() {
        box = new Box();
        box.setBoxId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        box.setBoxName("Jakarta");
        box.setBoxDescription("Oleh-oleh dari abang none");
    }

    @Test
    void testExecuteDelete() {
        RatingReview ratingReview = new RatingReview(box, "jajang", "bagus boxnya, ada  bau kali ciliwung", 4);
        ratingReviewRepository.save(ratingReview);

        DeleteRatingReviewCommand deleteRatingReviewCommand = new DeleteRatingReviewCommand(ratingReview.getRatingReviewId(), ratingReviewRepository);
        deleteRatingReviewCommand.execute();

        Optional<RatingReview> deletedReview = ratingReviewRepository.findById(ratingReview.getRatingReviewId());
        assertFalse(deletedReview.isPresent());
    }
}
