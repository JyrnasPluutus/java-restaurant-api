package ee.itcollege.restaurantapi.controller;

import ee.itcollege.restaurantapi.model.Dish;
import ee.itcollege.restaurantapi.model.Order;
import ee.itcollege.restaurantapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DishController dishController;
    @Autowired
    private UserController userController;

    @GetMapping
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @GetMapping("{id}")
    public Order findOne(@PathVariable Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Order id doesn't exist"));
    }

    @PostMapping
    public Order submitOrder(@RequestBody Order order) {
        validateOrder(order);
        order.setUserObj(userController.findOne(order.getUserObj().getId()));
        List<Dish> dishes = new ArrayList<>();
        for (Dish dish : order.getDishes()) {
            dishes.add(dishController.findOne(dish.getId()));
        }
        order.setDishes(dishes);
        return orderRepository.save(order);
    }

    private void validateOrder(Order order) {
        if (order.getDishes() == null) {
            throw new ResponseStatusException(BAD_REQUEST, "Invalid dishes");
        } else if (order.getUserObj() == null) {
            throw new ResponseStatusException(BAD_REQUEST, "Invalid user");
        }
    }
}