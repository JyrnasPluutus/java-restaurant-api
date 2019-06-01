package ee.itcollege.restaurantapi.repository;

import ee.itcollege.restaurantapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}