package id.ac.ui.cs.advprog.koleksikota.ratingreview.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    void setup() {
        ratingReviewList = new ArrayList<>();

        List<Box> boxes = new ArrayList<>();
        Box box1 = new Box();
        box1.setBoxId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        box1.setBoxName("Jakarta");
        box1.setBoxDescription("Oleh-oleh dari abang none");
        boxes.add(box1);

        Box box = boxes.get(0);

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
        Mockito.when(ratingReviewRepository.findById(String.valueOf(ratingReview.getRatingReviewId()))).thenReturn(Optional.of(ratingReview));

        RatingReview result = (RatingReview) ratingReviewRepository.save(ratingReview);

        Optional<RatingReview> findResult = ratingReviewRepository.findById(String.valueOf(ratingReview.getRatingReviewId()));
        assertEquals(ratingReview.getRatingReviewId(), result.getRatingReviewId());
        assertEquals(ratingReview.getRatingReviewId(), findResult.get().getRatingReviewId());
        assertEquals(ratingReview.getUserId(), findResult.get().getUserId());
        assertEquals(ratingReview.getBox(), findResult.get().getBox());
        assertEquals(ratingReview.getReview(), findResult.get().getReview());
        assertEquals(ratingReview.getRating(), findResult.get().getRating());    }

    @Test
    void testFindByIdIfIdFound() {
        Mockito.when(ratingReviewRepository.saveAll(ratingReviewList)).thenReturn(ratingReviewList);
        Mockito.when(ratingReviewRepository.findById(String.valueOf(ratingReviewList.get(0).getRatingReviewId())))
                .thenReturn(Optional.of(ratingReviewList.get(0)));

        ratingReviewRepository.saveAll(ratingReviewList);

        Optional<RatingReview> found = ratingReviewRepository.findById(String.valueOf(ratingReviewList.get(0).getRatingReviewId()));
        assertEquals(ratingReviewList.get(0).getRatingReviewId(), found.get().getRatingReviewId());
        assertEquals(ratingReviewList.get(0).getUserId(), found.get().getUserId());
        assertEquals(ratingReviewList.get(0).getBox(), found.get().getBox());
        assertEquals(ratingReviewList.get(0).getReview(), found.get().getReview());
        assertEquals(ratingReviewList.get(0).getRating(), found.get().getRating());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        Mockito.when(ratingReviewRepository.saveAll(ratingReviewList)).thenReturn(ratingReviewList);
        Mockito.when(ratingReviewRepository.findById("ong pls help")).thenReturn(Optional.empty());

        ratingReviewRepository.saveAll(ratingReviewList);

        Optional<RatingReview> found = ratingReviewRepository.findById("ong pls help");
        assertFalse(found.isPresent());
    }

    @Test
    void testDelete() {
        Mockito.when(ratingReviewRepository.saveAll(ratingReviewList)).thenReturn(ratingReviewList);
        Mockito.doNothing().when(ratingReviewRepository).deleteById(String.valueOf(ratingReviewList.get(0).getRatingReviewId()));
        Mockito.when(ratingReviewRepository.findById(String.valueOf(ratingReviewList.get(0).getRatingReviewId()))).
                thenReturn(Optional.empty());

        ratingReviewRepository.saveAll(ratingReviewList);

        ratingReviewRepository.deleteById(String.valueOf(ratingReviewList.get(0).getRatingReviewId()));

        Optional<RatingReview> findResult = ratingReviewRepository.findById(String.valueOf(ratingReviewList.get(0).getRatingReviewId()));
        assertFalse(findResult.isPresent());
    }
}