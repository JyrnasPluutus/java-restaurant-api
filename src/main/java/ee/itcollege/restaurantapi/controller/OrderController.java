package ee.itcollege.restaurantapi.controller;

import ee.itcollege.restaurantapi.model.Order;
import ee.itcollege.restaurantapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DishController dishController;

    @GetMapping
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @GetMapping("{id}")
    public Order findOne(@PathVariable Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "ID doesn't exist"));
    }
    @PostMapping
    public Order submitOrder(@RequestBody Order order) {
        validateOrder(order);
        //List<Dish> list = new LinkedList<>();
        //for (Dish d : order.getDishes())
        //    list.add(dishController.findOne(d.getId()));
        //order.setDishes(list);
        return orderRepository.save(order);
    }

    private void validateOrder(Order order) {
        if(order.getUserObj() == null || order.getDishes() == null) {
            throw new ResponseStatusException(BAD_REQUEST, "Invalid order");
        }
    }
}