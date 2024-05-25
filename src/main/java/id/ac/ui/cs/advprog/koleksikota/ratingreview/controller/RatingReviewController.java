package id.ac.ui.cs.advprog.koleksikota.ratingreview.controller;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.command.CreateRatingReviewCommand;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.command.DeleteRatingReviewCommand;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.command.EditRatingReviewCommand;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.command.RatingReviewCommand;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.RatingReview;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.repository.RatingReviewRepository;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.service.RatingReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@RestController
@EnableAsync
@RequestMapping("/rating-review")
public class RatingReviewController {

    @Autowired
    RatingReviewService ratingReviewService;

    @Autowired
    RatingReviewRepository ratingReviewRepository;

    @GetMapping
    public String renderRatingReviewPage() {
        return "HomePage";
    }

    @Async
    @PostMapping("/{boxId}/create-rating-review")
    public CompletableFuture<ResponseEntity<Optional<RatingReview>>> createRatingReview(@PathVariable String boxId, @RequestBody Map<String, Object> requestBody){
        String reviewer = requestBody.get("reviewer").toString();
        int rating = Integer.parseInt(requestBody.get("rating").toString());
        String review = requestBody.get("review").toString();
        RatingReview ratingReview = new RatingReview(boxId, reviewer, review, rating);

        RatingReviewCommand createRatingReviewCommand = new CreateRatingReviewCommand(ratingReview, ratingReviewRepository);
        return CompletableFuture.completedFuture(ResponseEntity.ok(ratingReviewService.executeCommand(createRatingReviewCommand)));
    }

    @Async
    @DeleteMapping("/{ratingReviewId}/delete-rating-review")
    public CompletableFuture<ResponseEntity<Optional<RatingReview>>> deleteRatingReview(@PathVariable String ratingReviewId){
        RatingReviewCommand deleteRatingReviewCommand = new DeleteRatingReviewCommand(ratingReviewId, ratingReviewRepository);
        ratingReviewService.executeCommand(deleteRatingReviewCommand);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @PutMapping("/{ratingReviewId}/edit-rating-review")
    public CompletableFuture<ResponseEntity<Optional<RatingReview>>> editRatingReview(@PathVariable String ratingReviewId, @RequestBody Map<String, String> requestBody){
        Optional<RatingReview> optionalRatingReview = ratingReviewService.findReviewById(ratingReviewId);
        if (optionalRatingReview.isPresent()){
            RatingReview ratingReview = optionalRatingReview.get();
            ratingReview.setRating(Integer.parseInt(requestBody.get("rating")));
            ratingReview.setReview(requestBody.get("review"));

            RatingReviewCommand editRatingReviewCommand = new EditRatingReviewCommand(ratingReviewRepository, ratingReview);
            return CompletableFuture.completedFuture(ResponseEntity.ok(ratingReviewService.executeCommand(editRatingReviewCommand)));
        } else {
            return CompletableFuture.completedFuture(ResponseEntity.notFound().build());
        }
    }
}