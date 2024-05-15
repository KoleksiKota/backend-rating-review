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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/review")
public class RatingReviewController {

    @Autowired
    RatingReviewService ratingReviewService;

    @Autowired
    RatingReviewRepository ratingReviewRepository;

    @GetMapping
    public String renderRatingReviewPage() {
        return "HomePage";
    }

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody RatingReview ratingReview){
        Map<String, Object> res = new HashMap<>();
        try {
            RatingReviewCommand createRatingReviewCommand = new CreateRatingReviewCommand(ratingReview, ratingReviewRepository);
            ratingReviewService.executeCommand(createRatingReviewCommand);

            res.put("ratingReview", createRatingReviewCommand);
            res.put("message", "Rating & Review Created Successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(res);

        } catch (Exception e){
            res.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.put("error", e.getMessage());
            res.put("message", "Server Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @DeleteMapping("/{ratingReviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable("ratingReviewId") String id){
        Map<String, Object> res = new HashMap<>();
        try{
            RatingReviewCommand deleteRatingReviewCommand = new DeleteRatingReviewCommand(id, ratingReviewRepository);
            ratingReviewService.executeCommand(deleteRatingReviewCommand);

            res.put("code", HttpStatus.OK.value());
            res.put("message", "Rating & Review Deleted Successfully");
            return ResponseEntity.status(HttpStatus.OK).body(res);

        } catch (Exception e){
            res.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.put("error", e.getMessage());
            res.put("message", "Server Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @PutMapping
    public ResponseEntity<?> editReview(@RequestBody RatingReview ratingReview){
        Map<String, Object> res = new HashMap<>();
        try{
            RatingReviewCommand editRatingReviewCommand = new EditRatingReviewCommand(ratingReviewRepository, ratingReview);
            ratingReviewService.executeCommand(editRatingReviewCommand);

            res.put("ratingReview", editRatingReviewCommand);
            res.put("message", "Rating & Review with Id: " + ratingReview.getRatingReviewId() + " updated Successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(res);

        } catch (Exception e){
            res.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.put("error", e.getMessage());
            res.put("message", "Server Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @GetMapping("/findAllRatingReview")
    public ResponseEntity<?> findAllRatingReview(){
        try {
            List<RatingReview> allRatingReview = ratingReviewService.findAll();
            return ResponseEntity.ok(allRatingReview);
        }catch (Exception e){
            Map<String, Object> response = new HashMap<>();
            response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.put("error", e.getMessage());
            response.put("message", "Server Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}