package id.ac.ui.cs.advprog.koleksikota.ratingreview.repository;

import id.ac.ui.cs.advprog.koleksikota.ratingreview.model.RatingReview;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public interface RatingReviewRepository extends CrudRepository<RatingReview, String> {

}