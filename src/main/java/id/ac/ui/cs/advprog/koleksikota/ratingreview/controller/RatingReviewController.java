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

@CrossOrigin
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
    public CompletableFuture<ResponseEntity<Optional<RatingReview>>> createRatingReview(@PathVariable String boxId, @RequestBody Map<String, String> requestBody){
        String reviewer = requestBody.get("reviewer");
        int rating = Integer.parseInt(requestBody.get("rating"));
        String review = requestBody.get("review");
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
    @PatchMapping("/{ratingReviewId}/edit-rating-review")
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

    @Async
    @GetMapping("/all")
    public CompletableFuture<ResponseEntity<List<RatingReview>>> getAllRatingReview(){
        List<RatingReview> allRatingReview = ratingReviewService.findAll();
        return CompletableFuture.completedFuture(ResponseEntity.ok(allRatingReview));
    }

    @Async
    @GetMapping("/{ratingReviewId}")
    public CompletableFuture<ResponseEntity<RatingReview>> getRatingReviewById(@PathVariable String ratingReviewId){
        Optional<RatingReview> optionalRatingReview = ratingReviewService.findReviewById(ratingReviewId);
        if(optionalRatingReview.isPresent()){
            RatingReview ratingReview = optionalRatingReview.get();
            return CompletableFuture.completedFuture(ResponseEntity.ok(ratingReview));
        } else {
            return CompletableFuture.completedFuture(ResponseEntity.notFound().build());
        }
    }

    @Async
    @GetMapping("user/{userId}")
    public CompletableFuture<ResponseEntity<List<RatingReview>>> getRatingReviewByUserId(@PathVariable String userId){
        List<RatingReview> userRatingReviews = ratingReviewService.findAllByUserId(userId);
        return CompletableFuture.completedFuture(ResponseEntity.ok(userRatingReviews));
    }

    @Async
    @GetMapping("subscription-box/{subscriptionBoxId}")
    public CompletableFuture<ResponseEntity<List<RatingReview>>> getRatingReviewBySubscriptionBoxId(@PathVariable String subscriptionBoxId){
        List<RatingReview> subsBoxRatingReviews = ratingReviewService.findAllBySubscriptionBoxId(subscriptionBoxId);
        return CompletableFuture.completedFuture(ResponseEntity.ok(subsBoxRatingReviews));
    }

    @Async
    @PatchMapping("/{ratingReviewId}/change-status")
    public CompletableFuture<ResponseEntity<Optional<RatingReview>>> editApprovalStatus(@PathVariable String ratingReviewId, @RequestBody Map<String, String> requestBody){
        String newStatus = requestBody.get("status");
        RatingReview updatedRatingReview = ratingReviewService.updateReviewStatus(ratingReviewId, newStatus);
        return CompletableFuture.completedFuture(ResponseEntity.ok(Optional.ofNullable(updatedRatingReview)));
    }
}