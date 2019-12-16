package api.wp.finki.ukim.mk.lab3.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Pizzas")
public class Pizza {
    @Id
    String name;
    String description;
    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    List<Ingredient> ingredients;
    boolean veggie;
}

