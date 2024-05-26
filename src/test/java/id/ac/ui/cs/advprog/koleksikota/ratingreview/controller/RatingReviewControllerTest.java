package id.ac.ui.cs.advprog.koleksikota.ratingreview.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.when;

import java.util.*;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.command.DeleteRatingReviewCommand;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.command.EditRatingReviewCommand;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.RatingReview;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.command.CreateRatingReviewCommand;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.repository.RatingReviewRepository;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.service.RatingReviewService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(RatingReviewController.class)
public class RatingReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RatingReviewService ratingReviewService;

    @MockBean
    private RatingReviewRepository ratingReviewRepository;

    @InjectMocks
    private RatingReviewController ratingReviewController;

    @Test
    public void testCreateRatingReview() throws Exception {
        String customerId1 = "maman resing";
        String subscriptionBoxId1 = "95920130-6f6e-41d7-b5d7-cbf5a8fa2416";
        int rating1 = 4;
        String reviewText1 = "jadi pembalap";

        RatingReview ratingReview = new RatingReview(customerId1, subscriptionBoxId1, reviewText1, rating1);
        CreateRatingReviewCommand createRatingReviewCommand = new CreateRatingReviewCommand(ratingReview, ratingReviewRepository);

        when(ratingReviewService.executeCommand(createRatingReviewCommand)).thenReturn(Optional.of(ratingReview));

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("customerId", customerId1);
        requestBody.put("review", reviewText1);
        requestBody.put("rating", String.valueOf(rating1));

        MvcResult mvcResult = mockMvc.perform(post("/rating-review/" + subscriptionBoxId1 + "/create-rating-review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestBody)))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testEditRatingReview() throws Exception {
        String customerId1 = "maman resing";
        String subscriptionBoxId1 = "95920130-6f6e-41d7-b5d7-cbf5a8fa2416";
        int rating1 = 4;
        String reviewText1 = "jadi pembalap";
        String ratingReviewId = "f88b3069-21e4-4f99-91ef-26a991817597";

        RatingReview existingRatingReview = new RatingReview(customerId1, subscriptionBoxId1, reviewText1, rating1);
        existingRatingReview.setRatingReviewId(ratingReviewId);

        String updatedReviewText = "Updated review text";
        int updatedRating = 5;

        RatingReview updatedRatingReview = new RatingReview(customerId1, subscriptionBoxId1, updatedReviewText, updatedRating);
        updatedRatingReview.setRatingReviewId(ratingReviewId);

        when(ratingReviewService.findReviewById(ratingReviewId)).thenReturn(Optional.of(existingRatingReview));

        EditRatingReviewCommand editRatingReviewCommand = new EditRatingReviewCommand(ratingReviewRepository, updatedRatingReview);
        when(ratingReviewService.executeCommand(editRatingReviewCommand)).thenReturn(Optional.of(updatedRatingReview));

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("review", updatedReviewText);
        requestBody.put("rating", String.valueOf(updatedRating));

        MvcResult mvcResult = mockMvc.perform(patch("/rating-review/" + ratingReviewId + "/edit-rating-review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestBody)))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testDeleteRatingReview() throws Exception {
        String customerId1 = "maman resing";
        String subscriptionBoxId1 = "95920130-6f6e-41d7-b5d7-cbf5a8fa2416";
        int rating1 = 4;
        String reviewText1 = "jadi pembalap";

        RatingReview ratingReview = new RatingReview(customerId1, subscriptionBoxId1, reviewText1, rating1);
        ratingReview.setRatingReviewId("f88b3069-21e4-4f99-91ef-26a991817597");

        DeleteRatingReviewCommand deleteRatingReviewCommand = new DeleteRatingReviewCommand(ratingReview.getRatingReviewId(), ratingReviewRepository);

        when(ratingReviewService.executeCommand(deleteRatingReviewCommand)).thenReturn(Optional.empty());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/rating-review/" + ratingReview.getRatingReviewId() + "/delete-rating-review"))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testGetAllRatingReview() throws Exception {
        List<RatingReview> allRatingReview = new ArrayList<>();

        String customerId1 = "maman resing";
        String subscriptionBoxId1 = "95920130-6f6e-41d7-b5d7-cbf5a8fa2416";
        int rating1 = 4;
        String reviewText1 = "jadi pembalap";

        RatingReview ratingReview = new RatingReview(customerId1, subscriptionBoxId1, reviewText1, rating1);
        allRatingReview.add(ratingReview);

        when(ratingReviewService.findAll()).thenReturn(allRatingReview);

        MvcResult mvcResult = mockMvc.perform(get("/rating-review/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testGetRatingReviewById() throws Exception {
        String customerId1 = "maman resing";
        String subscriptionBoxId1 = "95920130-6f6e-41d7-b5d7-cbf5a8fa2416";
        int rating1 = 4;
        String reviewText1 = "jadi pembalap";

        RatingReview ratingReview = new RatingReview(customerId1, subscriptionBoxId1, reviewText1, rating1);
        ratingReview.setRatingReviewId("f88b3069-21e4-4f99-91ef-26a991817597");

        Optional<RatingReview> optionalRatingReview = Optional.of(ratingReview);

        when(ratingReviewService.findReviewById(ratingReview.getRatingReviewId())).thenReturn(optionalRatingReview);

        MvcResult mvcResult = mockMvc.perform(get("/rating-review/f88b3069-21e4-4f99-91ef-26a991817597")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testGetRatingReviewByUserId() throws Exception {
        List<RatingReview> ratingReviewsByUser = new ArrayList<>();

        String userId = "maman resing";
        String subscriptionBoxId = "95920130-6f6e-41d7-b5d7-cbf5a8fa2416";
        int rating = 4;
        String reviewText = "jadi pembalap";

        RatingReview ratingReview = new RatingReview(userId, subscriptionBoxId, reviewText, rating);
        ratingReviewsByUser.add(ratingReview);

        when(ratingReviewService.findAllByUserId(userId)).thenReturn(ratingReviewsByUser);

        MvcResult mvcResult = mockMvc.perform(get("/rating-review/user/" + userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testGetRatingReviewBySubscriptionBoxId() throws Exception {
        List<RatingReview> ratingReviewsOnBox = new ArrayList<>();

        String userId = "maman resing";
        String subscriptionBoxId = "95920130-6f6e-41d7-b5d7-cbf5a8fa2416";
        int rating = 4;
        String reviewText = "jadi pembalap";

        RatingReview ratingReview = new RatingReview(userId, subscriptionBoxId,reviewText, rating);
        ratingReviewsOnBox.add(ratingReview);

        when(ratingReviewService.findAllBySubscriptionBoxId(subscriptionBoxId)).thenReturn(ratingReviewsOnBox);

        MvcResult mvcResult = mockMvc.perform(get("/rating-review/subscription-box/" + subscriptionBoxId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
}
