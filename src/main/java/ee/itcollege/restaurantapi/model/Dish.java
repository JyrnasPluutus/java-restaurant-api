package ee.itcollege.restaurantapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String category;
    private double price;
    private boolean vegan;
    private boolean vegetarian;
    private boolean glutenFree;
    private double rating;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long sum_ratings;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long n_ratings; // simpler than an array of ratings

    public Dish() {
    }

    public Dish(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public Dish(String name, String category, double price, boolean vegan, boolean vegetarian, boolean gluten_free) {
        this(name, category, price);
        this.vegan = vegan;
        this.vegetarian = vegetarian;
        this.glutenFree = gluten_free;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public void addRating(int rating) {
        if (!(rating >= 0 && rating <= 5))
            return;
        this.n_ratings++;
        this.sum_ratings += rating;
        this.rating = (double) this.sum_ratings / (double) this.n_ratings;
    }
}
