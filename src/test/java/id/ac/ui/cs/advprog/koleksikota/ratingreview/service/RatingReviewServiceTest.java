package id.ac.ui.cs.advprog.koleksikota.ratingreview.service;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.command.RatingReviewCommand;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.Box;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.RatingReview;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.repository.RatingReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RatingReviewServiceTest {
    @Mock
    RatingReviewRepository ratingReviewRepository;

    @InjectMocks
    RatingReviewServiceImpl ratingReviewService;

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
    void testExecuteCommand() {
        RatingReview ratingReview = new RatingReview(box, "jajang", "bagus boxnya, ada  bau kali ciliwung", 4);
        RatingReviewCommand command = () -> ratingReview;

        Optional<RatingReview> result = ratingReviewService.executeCommand(command);
        assertEquals(ratingReview, result);
    }

    @Test
    void testFindAllEmpty() {
        List<RatingReview> ratingReviewList = new ArrayList<>();
        Mockito.when(ratingReviewRepository.findAll()).thenReturn(ratingReviewList.iterator());

        List<RatingReview> allRatingReview = ratingReviewService.findAll();
        assertTrue(allRatingReview.isEmpty());
    }

    @Test
    void testFindAllNotEmpty() {
        List<RatingReview> ratingReviewList = new ArrayList<>();
        RatingReview ratingReview1 = new RatingReview(box, "maman resing", "gg geming", 5);
        RatingReview ratingReview2 = new RatingReview(box, "samlekum mamang", "jelek", 1);
        ratingReviewList.add(ratingReview1);
        ratingReviewList.add(ratingReview2);

        Mockito.when(ratingReviewRepository.findAll()).thenReturn(ratingReviewList.iterator());
        List<RatingReview> allRatingReview = ratingReviewService.findAll();

        assertFalse(allRatingReview.isEmpty());
        assertEquals(2, allRatingReview.size());
        assertTrue(allRatingReview.contains(ratingReview1));
        assertTrue(allRatingReview.contains(ratingReview2));
    }
}
