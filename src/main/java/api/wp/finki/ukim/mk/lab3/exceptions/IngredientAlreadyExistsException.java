package api.wp.finki.ukim.mk.lab3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Ingredient with same name already exists")
public class IngredientAlreadyExistsException extends Exception {
    public IngredientAlreadyExistsException(String name) {
        super("Ingredient with name " + name + " already exists");
    }
}
