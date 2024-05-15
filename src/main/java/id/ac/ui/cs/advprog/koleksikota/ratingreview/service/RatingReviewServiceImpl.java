package id.ac.ui.cs.advprog.koleksikota.ratingreview.service;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.command.RatingReviewCommand;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.RatingReview;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.repository.RatingReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class RatingReviewServiceImpl implements RatingReviewService {
    private final RatingReviewRepository ratingReviewRepository;

    private final RestTemplate restTemplate;

    @Autowired
    public RatingReviewServiceImpl(RatingReviewRepository ratingReviewRepository, RestTemplateBuilder restTemplateBuilder) {
        this.ratingReviewRepository = ratingReviewRepository;
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Optional<RatingReview> executeCommand(RatingReviewCommand command) {
        return command.execute();
    }

    @Override
    public List<RatingReview> findAll() {
        Iterable<RatingReview> ratingReviewIterator = ratingReviewRepository.findAll();
        List<RatingReview> allRatingReview = new ArrayList<>();
        ratingReviewIterator.forEach(allRatingReview::add);
        return allRatingReview;
    }

    @Override
    public RatingReview findRatingReviewByBox(String productId) {
        String productApi = "http://34.124.239.11/";
        String url = productApi + "/rating-reviews/" + productId;
        ResponseEntity<RatingReview> response = restTemplate.getForEntity(url, RatingReview.class);
        return response.getBody();
    }
}
