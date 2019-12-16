package api.wp.finki.ukim.mk.lab3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NonVeggieIngredientInVeggiePizzaException extends RuntimeException {
    public NonVeggieIngredientInVeggiePizzaException() {
        super("Non veggie ingredients are not allowed in a veggie pizza");
    }
}
