package ee.itcollege.restaurantapi.controller;

import ee.itcollege.restaurantapi.model.Dish;
import ee.itcollege.restaurantapi.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.function.Supplier;

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

    @GetMapping("{id}")
    public Dish findOne(@PathVariable Long id) {
        return dishRepository.findById(id)
                .orElseThrow(exceptionSupplier());
    }

//    {
//        "id": 3,
//        "name": "Ramen",
//        "category": "Soup"
//    }
    @PostMapping
    public Dish save(@RequestBody Dish dish) {
        validate(dish);

        return dishRepository.save(dish);
    }

    @PutMapping("{id}")
    public Dish update(@RequestBody Dish dish, @PathVariable Long id) {
        validate(dish);
        Dish oldDish = findOne(id);
        oldDish.setName(dish.getName());
        oldDish.setcategory(dish.getCategory());

        return dishRepository.save(dish);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        Dish dish = findOne(id);
        dishRepository.delete(dish);
    }

    private void validate(@RequestBody Dish dish) {
        if(dish.getName() == null){
            throw new ResponseStatusException(BAD_REQUEST, "Name is null");
        } if(dish.getCategory() == null){
            throw new ResponseStatusException(BAD_REQUEST, "Category is null");
        }
    }

    private Supplier<ResponseStatusException> exceptionSupplier() {
        return () -> new ResponseStatusException(BAD_REQUEST, "Id does'nt exist");
    }
}