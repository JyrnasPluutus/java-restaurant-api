package ee.itcollege.restaurantapi;

import ee.itcollege.restaurantapi.model.Dish;
import ee.itcollege.restaurantapi.model.Order;
import ee.itcollege.restaurantapi.model.User;
import ee.itcollege.restaurantapi.repository.DishRepository;
import ee.itcollege.restaurantapi.repository.OrderRepository;
import ee.itcollege.restaurantapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class restaurantApiApplication {

    private static Logger logger = LoggerFactory.getLogger(restaurantApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(restaurantApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(DishRepository dishRepository,
                                  UserRepository userRepository,
                                  OrderRepository orderRepository) {
        return (args) -> {
            logger.info("Started creating Dish objects");
            Dish ramen = dishRepository.save(new Dish("Ramen", "Soup", 4.19));
            Dish kimchi = dishRepository.save(new Dish("Kimchi", "Side dish", 3.49));
            dishRepository.save(new Dish("Gluten-free cookie", "Snack", 1.49, true, true, true));

            logger.info("Started creating User objects");
            userRepository.save(new User("GuyDudeson"));
            User userTwo = userRepository.save(new User("UserTwo"));

            logger.info("Started creating Order object");
            List<Dish> dishes = Arrays.asList(ramen, kimchi);

            orderRepository.save(new Order(userTwo, dishes, "im going to die if you use anything related to water"));
        };
    }

}
