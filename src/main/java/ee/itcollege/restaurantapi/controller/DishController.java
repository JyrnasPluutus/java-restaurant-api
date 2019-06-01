package ee.itcollege.restaurantapi.controller;

import ee.itcollege.restaurantapi.model.Dish;
import ee.itcollege.restaurantapi.model.PostRating;
import ee.itcollege.restaurantapi.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishRepository dishRepository;

    @GetMapping
    public List<Dish> findAll() {
        return dishRepository.findAll();
    }

    @GetMapping("/special/{type}")
    public List<Dish> findSpecial(@PathVariable String type) {
        switch (type) {
            case "vegan":
                return dishRepository.findByVeganIsTrue();
            case "vegetarian":
                return dishRepository.findByVegetarianIsTrue();
            case "glutenfree":
                return dishRepository.findByGlutenFreeIsTrue();
        }
        throw new ResponseStatusException(BAD_REQUEST, "unknown type, known types: vegan, vegetarian, glutenfree");
    }

    @GetMapping("{id}")
    public Dish findOne(@PathVariable Long id) {
        return dishRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "ID doesn't exist"));
    }
    @PostMapping
    public Dish save(@RequestBody Dish dish) {
        validate_dish(dish);
        return dishRepository.save(dish);
    }

    @PutMapping("{id}")
    public Dish update(@RequestBody Dish dish, @PathVariable Long id) {
        validate_dish(dish);
        findOne(id); // result unused, generates exception if missing from DB
        dish.setId(id);
        return dishRepository.save(dish);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        Dish dish = findOne(id);
        dishRepository.delete(dish);
    }

    @PostMapping("/rate")
    public PostRating rate_dish(@RequestBody PostRating rating) {
        if(rating.getDishId() == null)
            throw new ResponseStatusException(BAD_REQUEST, "Dish id doesn't exist");
        if(rating.getRating() == null || !(rating.getRating() >= 0 && rating.getRating() <= 5))
            throw new ResponseStatusException(BAD_REQUEST, "Invalid rating");
        Dish dish = dishRepository.getOne(rating.getDishId());
        dish.addRating(rating.getRating().intValue());
        dishRepository.save(dish);
        rating.setRating(dish.getRating());
        return rating;
    }

    private void validate_dish(Dish dish) {
        if (dish.getName() == null)
            throw new ResponseStatusException(BAD_REQUEST, "Name is null");
        if (dish.getCategory() == null)
            throw new ResponseStatusException(BAD_REQUEST, "Category is null");
    }
}
