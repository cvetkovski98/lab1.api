package api.wp.finki.ukim.mk.lab3.services;

import api.wp.finki.ukim.mk.lab3.models.PizzaRequest;
import api.wp.finki.ukim.mk.lab3.models.entities.Ingredient;
import api.wp.finki.ukim.mk.lab3.models.entities.Pizza;

import java.util.List;

public interface PizzaService {
    Pizza create(PizzaRequest pizza);

    Pizza update(String id, PizzaRequest pizza);

    void delete(String id);

    List<Pizza> getPizzas(Integer totalIngredients);

    List<Ingredient> getCommonIngredients(String pizza1, String pizza2);
}
