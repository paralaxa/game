package sk.stopangin.game;

import lombok.Data;
import lombok.NonNull;
import sk.stopangin.movement.Movement;
import sk.stopangin.player.Player;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Optional;

@Data
public class Round<T extends Serializable, R> {
    private Optional<Movement<T>> actualPossition= Optional.empty();
    @NonNull
    private final Player<T> player;
    @NonNull
    private final LocalTime roundStart;
    private LocalTime roundEnd;
    @NonNull
    private final R data; //ie dice throw
    @NonNull
    private RoundStatus roundStatus;

    public Round(Player<T> player, LocalTime roundStart, R data) {
        this.player = player;
        this.roundStart = roundStart;
        this.data = data;
        this.roundStatus = new RoundStatus(RoundState.NEW);
    }
}
