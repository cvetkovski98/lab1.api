package api.wp.finki.ukim.mk.lab3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PizzaAlreadyExistsException extends RuntimeException {
    public PizzaAlreadyExistsException(String id) {
        super("Pizza with name " + id + " already exists");
    }
}
