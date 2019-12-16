package api.wp.finki.ukim.mk.lab3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MaximumSpicyIngredientLimitException extends RuntimeException {
    public MaximumSpicyIngredientLimitException() {
        super("The maximum amount of spicy ingredients on the menu is 3");
    }
}
