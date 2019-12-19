package api.wp.finki.ukim.mk.lab3.controllers;

import api.wp.finki.ukim.mk.lab3.exceptions.IngredientAlreadyExistsException;
import api.wp.finki.ukim.mk.lab3.exceptions.IngredientNotFoundException;
import api.wp.finki.ukim.mk.lab3.models.entities.Ingredient;
import api.wp.finki.ukim.mk.lab3.models.entities.Pizza;
import api.wp.finki.ukim.mk.lab3.services.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<String> createIngredient(@RequestBody Ingredient ingredient) {
        try {
            ingredientService.create(ingredient);
            return ResponseEntity.ok().body("");
        } catch (IngredientAlreadyExistsException e) {
            return new ResponseEntity<>(
                    "Ingredient with the same name already exists",
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PatchMapping("/{id}")
    public Ingredient updateIngredient(@PathVariable String id,
                                       @RequestBody Ingredient ingredient) {
        return this.ingredientService.update(id, ingredient);
    }

    @DeleteMapping("/{id}")
    public void deleteIngredient(@PathVariable String id) {
        this.ingredientService.delete(id);
    }

    @GetMapping
    public List<Ingredient> getIngredients(@RequestParam(required = false, defaultValue = "false") boolean pageable,
                                           @RequestParam(required = false, defaultValue = "10") int max,
                                           @RequestParam(required = false, defaultValue = "0") int page,
                                           @RequestParam(required = false, defaultValue = "5") int size,
                                           @RequestParam(required = false, defaultValue = "false") boolean spicy
    ) {
        return this.ingredientService.getAllIngredients(pageable, max, page, size, spicy);
    }

    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable String id) throws IngredientNotFoundException {
        return this.ingredientService.getIngredient(id);
    }

    @GetMapping("/{id}/pizzas")
    public List<Pizza> getIngredientPizzas(@PathVariable String id) throws IngredientNotFoundException {
        return this.ingredientService.getPizzasForIngredient(id);
    }

}
