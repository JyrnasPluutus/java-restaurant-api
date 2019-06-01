package ee.itcollege.restaurantapi.model;

import org.springframework.lang.Nullable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String comment;
    private Long dishId;
    @Nullable
    private Integer rating; // optional

    public Review() {
    }

    public Review(String comment, Long dishId, Integer rating) {
        this.comment = comment;
        this.dishId = dishId;
        this.rating = rating;
    }

    public Long getDishId() {
        return this.dishId;
    }

    public String getComment() {
        return comment;
    }

    public Integer getRating() {
        return rating;
    }
}
