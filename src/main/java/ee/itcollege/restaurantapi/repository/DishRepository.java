package ee.itcollege.restaurantapi.repository;

import ee.itcollege.restaurantapi.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {


}