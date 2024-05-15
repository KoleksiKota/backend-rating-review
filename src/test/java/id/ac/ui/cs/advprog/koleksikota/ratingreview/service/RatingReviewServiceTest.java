package id.ac.ui.cs.advprog.koleksikota.ratingreview.service;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.command.RatingReviewCommand;
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
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RatingReviewServiceTest {
    @InjectMocks
    RatingReviewServiceImpl ratingReviewService;

    @Mock
    RatingReviewRepository ratingReviewRepository;

    @Mock
    private RestTemplateBuilder restTemplateBuilder;

    @Mock
    private RestTemplate restTemplate;

    private Box box;

    @BeforeEach
    void setUp() {
        Mockito.lenient().when(restTemplateBuilder.build()).thenReturn(restTemplate);
        restTemplate = new RestTemplate();
        box = new Box();
        box.setBoxId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        box.setBoxName("Jakarta");
        box.setBoxDescription("Oleh-oleh dari abang none");
    }

    @Test
    void testExecuteCommand() {
        RatingReview ratingReview = new RatingReview(box, "jajang", "bagus boxnya, ada  bau kali ciliwung", 4);
        RatingReviewCommand command = () -> Optional.of(ratingReview);

        Optional<RatingReview> result = ratingReviewService.executeCommand(command);
        assertEquals(ratingReview, result.get());
    }

    @Test
    void testFindAllEmpty() {
        List<RatingReview> ratingReviewList = new ArrayList<>();
        when(ratingReviewRepository.findAll()).thenReturn(ratingReviewList);

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

        when(ratingReviewRepository.findAll()).thenReturn(ratingReviewList);
        List<RatingReview> allRatingReview = ratingReviewService.findAll();

        assertFalse(allRatingReview.isEmpty());
        assertEquals(2, allRatingReview.size());
        assertTrue(allRatingReview.contains(ratingReview1));
        assertTrue(allRatingReview.contains(ratingReview2));
    }

//    @Test
//    public void testGetRatingReviewByBox() {
//        String boxId = box.getBoxId();
//        RatingReview expectedRatingReview = new RatingReview(box, "maman resing", "gg geming", 5);
//
//        when(restTemplate.getForEntity("http://34.124.239.11/" + boxId, RatingReview.class)).thenReturn(ResponseEntity.ok(expectedRatingReview));
//        RatingReview actualRatingReview = ratingReviewService.findRatingReviewByBox(boxId);
//        assertEquals(expectedRatingReview, actualRatingReview);
//    }
}
