package api.wp.finki.ukim.mk.lab3.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Ingredients")
public class Ingredient {
    @Id
    String name;
    boolean spicy;
    boolean veggie;
    float amount;
    @JsonIgnore
    @ManyToMany(mappedBy = "ingredients",
            fetch = FetchType.EAGER
    )
    List<Pizza> pizzas;

    @PreRemove
    private void removeIngredientFromPizzas() {
        for (Pizza p : pizzas) {
            p.ingredients.remove(this);
        }
    }
}
