package api.wp.finki.ukim.mk.lab3.services.impl;

import api.wp.finki.ukim.mk.lab3.exceptions.IngredientAlreadyExistsException;
import api.wp.finki.ukim.mk.lab3.exceptions.IngredientNotFoundException;
import api.wp.finki.ukim.mk.lab3.exceptions.MaximumSpicyIngredientLimitException;
import api.wp.finki.ukim.mk.lab3.models.entities.Ingredient;
import api.wp.finki.ukim.mk.lab3.models.entities.Pizza;
import api.wp.finki.ukim.mk.lab3.repository.IngredientRepository;
import api.wp.finki.ukim.mk.lab3.services.IngredientService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient create(Ingredient ingredient) throws IngredientAlreadyExistsException {
        Optional<Ingredient> promise = ingredientRepository.findById(ingredient.getName());
        if (promise.isPresent()) {
            throw new IngredientAlreadyExistsException(ingredient.getName());
        }
        if (ingredient.isSpicy() && !isNewSpicyAllowed()) {
            throw new MaximumSpicyIngredientLimitException();
        }
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient update(String id, Ingredient ingredient) {
        if (ingredient.isSpicy() && !isNewSpicyAllowed()) {
            throw new MaximumSpicyIngredientLimitException();
        }
        if (ingredientRepository.findById(id).isEmpty()) {
            throw new IngredientNotFoundException(id);
        }
        ingredientRepository.deleteById(id);
        return ingredientRepository.save(ingredient);
    }

    @Override
    public void delete(String id) {
        if (ingredientRepository.findById(id).isEmpty()) {
            throw new IngredientNotFoundException(id);
        }
        ingredientRepository.deleteById(id);
    }

    @Override
    public List<Ingredient> getAllIngredients(boolean pageable, int max, int page, int size, boolean spicy) {
        if (pageable) {
            if (spicy) {
                return ingredientRepository.findAllBySpicy(true, PageRequest.of(page, size)).getContent();
            } else {
                return ingredientRepository.findAll(PageRequest.of(page, size)).getContent();
            }
        } else {
            if (spicy) {
                return ingredientRepository.findAllBySpicy(true);
            } else {
                return ingredientRepository.findAll();
            }
        }
    }

    @Override
    public Ingredient getIngredient(String id) {
        Optional<Ingredient> promise = ingredientRepository.findById(id);
        if (promise.isPresent()) {
            return promise.get();
        } else {
            throw new IngredientNotFoundException(id);
        }
    }

    @Override
    public List<Pizza> getPizzasForIngredient(String id) {
        Optional<Ingredient> promise = ingredientRepository.findById(id);
        if (promise.isPresent()) {
            return promise.get().getPizzas();
        } else {
            throw new IngredientNotFoundException(id);
        }
    }

    private boolean isNewSpicyAllowed() {
        return ingredientRepository.findAllBySpicy(true).size() < 3;
    }
}
