package ee.itcollege.restaurantapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User userObj;
    @NotNull
    @Column
    @ElementCollection
    private List<Dish> dishes;
    private Double total_price;
    private String comment;

    public Order() {
    }

    public Order(User user, List<Dish> dishes, String comment) {
        this.userObj = user;
        this.dishes = dishes;
        this.comment = comment;
        updateTotal_price();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
        updateTotal_price();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUserObj() {
        return userObj;
    }

    public void setUserObj(User userObj) {
        this.userObj = userObj;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }

    private void updateTotal_price() {
        this.total_price = 0.0d;
        for (Dish dish : this.dishes) {
            this.total_price += dish.getPrice();
        }
    }
}