package sk.stopangin.repository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Game not found")
public class GameRepositoryException extends RuntimeException {
    public GameRepositoryException(String message) {
        super(message);
    }

    public GameRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
