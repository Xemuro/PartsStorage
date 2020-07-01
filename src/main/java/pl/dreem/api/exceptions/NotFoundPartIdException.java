package pl.dreem.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundPartIdException extends RuntimeException {

    public NotFoundPartIdException() {
        super("Not found partId for action.");
    }
}
