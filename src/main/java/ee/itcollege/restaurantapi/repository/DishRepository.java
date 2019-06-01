package ee.itcollege.restaurantapi.repository;

import ee.itcollege.restaurantapi.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findByGlutenFreeIsTrue();

    List<Dish> findByVeganIsTrue();

    List<Dish> findByVegetarianIsTrue();
}
