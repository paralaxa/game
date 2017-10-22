package sk.stopangin.repository;

public class GameRepositoryException extends RuntimeException {
    public GameRepositoryException(String message) {
        super(message);
    }

    public GameRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
