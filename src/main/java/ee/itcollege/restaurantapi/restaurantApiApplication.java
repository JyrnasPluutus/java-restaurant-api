package ee.itcollege.restaurantapi;

import ee.itcollege.restaurantapi.model.Dish;
import ee.itcollege.restaurantapi.repository.DishRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class restaurantApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(restaurantApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(DishRepository repo) {
        return (args) -> {
            repo.save(new Dish("Ramen", "Soup", 4.19));
            repo.save(new Dish("Kimchi", "Side dish", 3.49));
            repo.save(new Dish("Gluten-free cookie", "Snack", 1.49, true, true, true));
        };
    }

}
