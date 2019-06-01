package ee.itcollege.restaurantapi.model;

//import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
public class PostRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long dishId;
    private Double rating;
    public PostRating() {
    }
    public PostRating(Long dishId, Double rating) {
        this.dishId = dishId;
        this.rating = rating;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getDishId() {
        return dishId;
    }
}
