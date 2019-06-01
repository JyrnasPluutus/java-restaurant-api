package ee.itcollege.restaurantapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String review;
    private Long dishId;
    private Integer rating; // optional

    public Review() {
    }

    public Review(String review, Long dishId, Integer rating) {
        this.review = review;
        this.dishId = dishId;
        this.rating = rating;
    }

    public Long getDishId() {
        return this.dishId;
    }

    public Long getId() {
        return id;
    }

    public Integer getRating() {
        return rating;
    }
}
