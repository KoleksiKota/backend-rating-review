package id.ac.ui.cs.advprog.koleksikota.ratingreview.service;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.command.EditRatingReviewCommand;
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

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
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

    @Test
    void testFindRatingReviewById(){
        RatingReview ratingReview = new RatingReview();
        String ratingReviewId = UUID.randomUUID().toString();
        ratingReview.setRatingReviewId(ratingReviewId);

        when(ratingReviewRepository.findById(ratingReviewId)).thenReturn(Optional.of(ratingReview));

        Optional<RatingReview> result = ratingReviewService.findReviewById(ratingReviewId);

        assertTrue(result.isPresent());
        assertEquals(ratingReviewId, result.get().getRatingReviewId());
    }

    @Test
    void testFindAllRatingReviewByUserId(){
        RatingReview ratingReview1 = new RatingReview();
        ratingReview1.setRatingReviewId("07f9b8b0-7257-4434-a5b9-79c9703f0760");
        RatingReview ratingReview2 = new RatingReview();
        ratingReview2.setRatingReviewId("07f9b8b0-7257-4434-a5b9-79c9703f0760");
        when(ratingReviewRepository.findAllByUserId("07f9b8b0-7257-4434-a5b9-79c9703f0760")).thenReturn(Arrays.asList(ratingReview1, ratingReview2));

        List<RatingReview> reviews = ratingReviewService.findAllByUserId("07f9b8b0-7257-4434-a5b9-79c9703f0760");
        assertFalse(reviews.isEmpty());
        assertEquals(reviews.size(), 2);
        assertEquals(reviews.get(0).getRatingReviewId(), ratingReview1.getRatingReviewId());
        assertEquals(reviews.get(1).getRatingReviewId(), ratingReview2.getRatingReviewId());
    }

    @Test
    void testFindRatingReviewBySubscriptionBoxId(){
        RatingReview ratingReview1 = new RatingReview();
        ratingReview1.setSubscriptionBoxId("99963276-4e60-4e9a-96ce-8d5a9957209d");
        RatingReview ratingReview2 = new RatingReview();
        ratingReview2.setSubscriptionBoxId("99963276-4e60-4e9a-96ce-8d5a9957209d");

        when(ratingReviewRepository.findAllBySubscriptionBoxId("99963276-4e60-4e9a-96ce-8d5a9957209d"))
                .thenReturn(Arrays.asList(ratingReview1,ratingReview2));

        List<RatingReview> ratingReviewsOnBox = ratingReviewService.findAllBySubscriptionBoxId("99963276-4e60-4e9a-96ce-8d5a9957209d");
        assertFalse(ratingReviewsOnBox.isEmpty());
        assertEquals(ratingReviewsOnBox.size(), 2);
        assertEquals(ratingReviewsOnBox.get(0).getSubscriptionBoxId(), ratingReview1.getSubscriptionBoxId());
        assertEquals(ratingReviewsOnBox.get(1).getSubscriptionBoxId(), ratingReview2.getSubscriptionBoxId());
    }

    @Test
    void testFindByIdThrowsException() {
        when(ratingReviewRepository.findById("not-exist")).thenReturn(Optional.empty());
        Optional<RatingReview> result = ratingReviewService.findReviewById("not-exist");
        assertFalse(result.isPresent());
    }
}
