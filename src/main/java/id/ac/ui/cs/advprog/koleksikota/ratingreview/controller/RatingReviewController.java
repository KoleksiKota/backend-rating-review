package id.ac.ui.cs.advprog.koleksikota.ratingreview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RatingReviewController {

    @GetMapping("/")
    public String homePage() {
        return "HomePage";
    }
}