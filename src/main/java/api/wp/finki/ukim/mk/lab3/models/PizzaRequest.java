package api.wp.finki.ukim.mk.lab3.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PizzaRequest {
    String name;
    String description;
    List<String> ingredientNames;
    boolean veggie;
}
