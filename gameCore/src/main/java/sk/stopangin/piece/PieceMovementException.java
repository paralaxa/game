package sk.stopangin.piece;

public class PieceMovementException extends RuntimeException {
    public PieceMovementException(String message) {
        super(message);
    }

    public PieceMovementException(String message, Throwable cause) {
        super(message, cause);
    }
}
