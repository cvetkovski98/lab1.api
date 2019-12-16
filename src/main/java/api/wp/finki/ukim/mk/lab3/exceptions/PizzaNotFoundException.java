package api.wp.finki.ukim.mk.lab3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PizzaNotFoundException extends RuntimeException {
    public PizzaNotFoundException(String id) {
        super("Pizza with name " + id + " does not exist");
    }
}
