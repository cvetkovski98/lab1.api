package api.wp.finki.ukim.mk.lab3.exceptions;

public class IngredientAlreadyExistsException extends RuntimeException {
    public IngredientAlreadyExistsException(String name) {
        super("Ingredient with name " + name + " already exists");
    }
}
