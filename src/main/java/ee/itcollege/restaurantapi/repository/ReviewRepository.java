package ee.itcollege.restaurantapi.repository;

import ee.itcollege.restaurantapi.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
