package ee.itcollege.restaurantapi.controller;

import ee.itcollege.restaurantapi.model.Order;
import ee.itcollege.restaurantapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.function.Supplier;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @GetMapping("{id}")
    public Order findOne(@PathVariable Long id) {
        return orderRepository.findById(id)
                .orElseThrow(exceptionSupplier());
    }

    private Supplier<ResponseStatusException> exceptionSupplier() {
        return () -> new ResponseStatusException(BAD_REQUEST, "Id does'nt exist");
    }
}