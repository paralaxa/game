package sk.stopangin.player;

public class PlayerExcpetion extends RuntimeException {
    public PlayerExcpetion(String message) {
        super(message);
    }

    public PlayerExcpetion(String message, Throwable cause) {
        super(message, cause);
    }
}
