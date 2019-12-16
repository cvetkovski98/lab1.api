package api.wp.finki.ukim.mk.lab3.services.impl;

import api.wp.finki.ukim.mk.lab3.exceptions.NonVeggieIngredientInVeggiePizzaException;
import api.wp.finki.ukim.mk.lab3.exceptions.PizzaAlreadyExistsException;
import api.wp.finki.ukim.mk.lab3.exceptions.PizzaNotFoundException;
import api.wp.finki.ukim.mk.lab3.models.PizzaRequest;
import api.wp.finki.ukim.mk.lab3.models.entities.Ingredient;
import api.wp.finki.ukim.mk.lab3.models.entities.Pizza;
import api.wp.finki.ukim.mk.lab3.repository.IngredientRepository;
import api.wp.finki.ukim.mk.lab3.repository.PizzaRepository;
import api.wp.finki.ukim.mk.lab3.services.PizzaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PizzaServiceImpl implements PizzaService {
    private final IngredientRepository ingredientRepository;
    private final PizzaRepository pizzaRepository;

    public PizzaServiceImpl(IngredientRepository ingredientRepository, PizzaRepository pizzaRepository) {
        this.ingredientRepository = ingredientRepository;
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public Pizza create(PizzaRequest pizza) {
        return savePizza(pizza);
    }

    @Override
    public Pizza update(String id, PizzaRequest pizza) {
        if (pizzaRepository.findById(id).isPresent()) {
            throw new PizzaAlreadyExistsException(id);
        }
        return savePizza(pizza);
    }

    @Override
    public void delete(String id) {
        if (pizzaRepository.findById(id).isPresent()) {
            pizzaRepository.deleteById(id);
        } else {
            throw new PizzaNotFoundException(id);
        }
    }

    @Override
    public List<Pizza> getPizzas(Integer totalIngredients) {
        if (totalIngredients != null) {
            return pizzaRepository.findPizzasWithIngredientCountLess(totalIngredients);
        } else {
            return pizzaRepository.findAll();
        }
    }

    @Override
    public List<Ingredient> getCommonIngredients(String pizza1, String pizza2) {
        return pizzaRepository.commonIngredients(pizza1, pizza2);
    }

    private Pizza savePizza(PizzaRequest pizza) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (String name : pizza.getIngredientNames()) {
            ingredientRepository.findById(name).ifPresent(ingredients::add);
        }
        if (pizza.isVeggie() && ingredients.stream().anyMatch(i -> !i.isVeggie())) {
            throw new NonVeggieIngredientInVeggiePizzaException();
        }
        Pizza pizzaToAdd = new Pizza(pizza.getName(), pizza.getDescription(), ingredients, pizza.isVeggie());
        return pizzaRepository.save(pizzaToAdd);
    }
}
