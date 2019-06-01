package ee.itcollege.restaurantapi.controller;

import ee.itcollege.restaurantapi.model.PostRating;
import ee.itcollege.restaurantapi.model.Review;
import ee.itcollege.restaurantapi.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    DishController dishController;

    @GetMapping
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @GetMapping("{id}")
    public Review findOne(@PathVariable Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "ID doesn't exist"));
    }

    @PostMapping
    public Review submitReview(@RequestBody Review review) {
        validateReview(review);
        if(review.getRating() != null) {
            PostRating rating = new PostRating(review.getDishId(), (double) review.getRating());
            dishController.rate_dish(rating);
        }
        reviewRepository.save(review);
        return review;
    }

    private void validateReview(Review review) {
        if(review.getDishId() == null)
            throw new ResponseStatusException(BAD_REQUEST, "Dish ID is null");
        if(review.getComment() == null)
            throw new ResponseStatusException(BAD_REQUEST, "Comment value is null");
        dishController.findOne(review.getDishId()); // return value ignored, throws exception if no dish is found
    }
}
