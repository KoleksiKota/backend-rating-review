package id.ac.ui.cs.advprog.koleksikota.ratingreview.service;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.command.RatingReviewCommand;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.enums.ApprovalStatus;
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
    public List<RatingReview> findAllByUserId(String userId){
        return ratingReviewRepository.findAllByUserId(userId);
    }

    @Override
    public List<RatingReview> findAllBySubscriptionBoxId(String subscriptionBoxId){
        return ratingReviewRepository.findAllBySubscriptionBoxId(subscriptionBoxId);
    }

    @Override
    public List<RatingReview> findAllByRatingAndSubscriptionBoxId(int rating, String subscriptionBoxId){
        return ratingReviewRepository.findAllByRatingAndSubscriptionBoxId(rating, subscriptionBoxId);
    }

    @Override
    public Optional<RatingReview> findReviewById(String reviewId){
        return ratingReviewRepository.findById(reviewId);
    }

    @Override
    public RatingReview updateReviewStatus(String idReview, String status) {
        RatingReview review = ratingReviewRepository.findById(idReview)
                .orElseThrow(() -> new IllegalArgumentException("Review not found with ID: " + idReview));
        if (status.equalsIgnoreCase(ApprovalStatus.APPROVED.toString())) {
            review.setApprovalStatus(ApprovalStatus.APPROVED.getValue());
        } else if (status.equalsIgnoreCase(ApprovalStatus.REJECTED.toString())) {
            review.setApprovalStatus(ApprovalStatus.REJECTED.getValue());
        } else {
            throw new IllegalArgumentException("Invalid status");
        }

        ratingReviewRepository.save(review);
        return review;
    }

    @Override
    public RatingReview findRatingReviewByBox(String productId) {
        String productApi = "http://34.124.239.11/";
        String url = productApi + "/rating-reviews/" + productId;
        ResponseEntity<RatingReview> response = restTemplate.getForEntity(url, RatingReview.class);
        return response.getBody();
    }
}
