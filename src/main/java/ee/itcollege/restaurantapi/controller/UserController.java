package ee.itcollege.restaurantapi.controller;

import ee.itcollege.restaurantapi.model.User;
import ee.itcollege.restaurantapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.function.Supplier;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("{id}")
    public User findOne(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(exceptionSupplier());
    }

    private Supplier<ResponseStatusException> exceptionSupplier() {
        return () -> new ResponseStatusException(BAD_REQUEST, "Id does'nt exist");
    }
}