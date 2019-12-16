package api.wp.finki.ukim.mk.lab3.controllers;

import api.wp.finki.ukim.mk.lab3.models.PizzaRequest;
import api.wp.finki.ukim.mk.lab3.models.entities.Ingredient;
import api.wp.finki.ukim.mk.lab3.models.entities.Pizza;
import api.wp.finki.ukim.mk.lab3.services.PizzaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @PostMapping
    public Pizza create(@RequestBody PizzaRequest pizza) {
        return this.pizzaService.create(pizza);
    }

    @PutMapping("/{id}")
    public Pizza update(@PathVariable String id,
                        @RequestBody PizzaRequest pizza) {
        return this.pizzaService.update(id, pizza);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.pizzaService.delete(id);
    }

    @GetMapping
    public List<Pizza> getPizzas(@RequestParam(required = false) Integer totalIngredients) {
        return this.pizzaService.getPizzas(totalIngredients);
    }

    @GetMapping("/compare")
    public List<Ingredient> getCommonIngredients(@RequestParam String pizza1,
                                                 @RequestParam String pizza2) {
        return this.pizzaService.getCommonIngredients(pizza1, pizza2);
    }

}
