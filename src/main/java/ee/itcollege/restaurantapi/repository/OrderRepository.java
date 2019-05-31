package ee.itcollege.restaurantapi.repository;

import ee.itcollege.restaurantapi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {


}