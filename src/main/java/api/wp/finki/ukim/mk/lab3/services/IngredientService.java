package api.wp.finki.ukim.mk.lab3.services;

import api.wp.finki.ukim.mk.lab3.exceptions.IngredientNotFoundException;
import api.wp.finki.ukim.mk.lab3.models.entities.Ingredient;
import api.wp.finki.ukim.mk.lab3.models.entities.Pizza;

import java.util.List;

public interface IngredientService {
    Ingredient create(Ingredient ingredient);

    Ingredient update(String id, Ingredient ingredient) throws IngredientNotFoundException;

    void delete(String id) throws IngredientNotFoundException;

    List<Ingredient> getAllIngredients(boolean pageable, int max, int page, int size, boolean spicy);

    Ingredient getIngredient(String id) throws IngredientNotFoundException;

    List<Pizza> getPizzasForIngredient(String id) throws IngredientNotFoundException;
}
