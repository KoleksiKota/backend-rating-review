package id.ac.ui.cs.advprog.koleksikota.ratingreview.service;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.command.RatingReviewCommand;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.RatingReview;
import id.ac.ui.cs.advprog.koleksikota.ratingreview.repository.RatingReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class RatingReviewServiceImpl implements RatingReviewService {
    @Autowired
    private RatingReviewRepository ratingReviewRepository;

    @Override
    public Optional<RatingReview> executeCommand(RatingReviewCommand command) {
        return Optional.ofNullable(command.execute());
    }

    @Override
    public List<RatingReview> findAll() {
        Iterator<RatingReview> ratingReviewIterator = ratingReviewRepository.findAll();
        List<RatingReview> allRatingReview = new ArrayList<>();
        ratingReviewIterator.forEachRemaining(allRatingReview::add);
        return allRatingReview;
    }
}
