package id.ac.ui.cs.advprog.koleksikota.ratingreview.model;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.enums.ApprovalStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RatingReviewTest {
    private List<Box> boxes;

    @BeforeEach
    void setUp() {
        this.boxes = new ArrayList<>();

        Box box1 = new Box();
        box1.setBoxId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        box1.setBoxName("Jakarta");
        box1.setBoxDescription("Oleh-oleh dari abang none");

        Box box2 = new Box();
        box2.setBoxId("a2c62328-4a37-4664-83c7-f32db8620155");
        box2.setBoxName("Lempuyangan");
        box2.setBoxDescription("Roti O stasiun lempuyangan");

        this.boxes.add(box1);
        this.boxes.add(box2);
    }

    @Test
    void testCreateReview(){
        Box box = boxes.getFirst();
        RatingReview ratingReview = new RatingReview(box, "jajang",
                "bagus boxnya, ada  bau kali ciliwung", 4);
        ratingReview.setRatingReviewId("cef7ead5-710d-4b10-8266-5e1c81a636fe");

        assertNotNull(ratingReview);
        assertEquals(box.getBoxId(), ratingReview.getBox().getBoxId());
        assertEquals("cef7ead5-710d-4b10-8266-5e1c81a636fe", String.valueOf(ratingReview.getRatingReviewId()));
        assertEquals("jajang", ratingReview.getUserId());
        assertEquals("bagus boxnya, ada  bau kali ciliwung", ratingReview.getReview());
        assertEquals(4, ratingReview.getRating());
        assertEquals(ApprovalStatus.PENDING.getValue(), ratingReview.getApprovalStatus());
    }

    @Test
    void testCreateReviewInvalidRating(){
        Box box = boxes.getFirst();
        assertThrows(IllegalArgumentException.class, () -> {
            RatingReview ratingReview = new RatingReview(box,"jajang",
                    "semua orang bingung", 6);
        });
    }
}
