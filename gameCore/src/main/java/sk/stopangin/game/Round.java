package sk.stopangin.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;
import sk.stopangin.movement.Movement;
import sk.stopangin.player.Player;

import java.io.Serializable;
import java.time.LocalTime;

@Data
public class Round<T extends Serializable, R> {
    private Movement<T> actualPossition;
    @NonNull
    private final Player<T> player;
    @NonNull
    private final LocalTime roundStart;
    @JsonIgnore
    private LocalTime roundEnd;
    @NonNull
    private final R data; //hod kockou napr.
    @NonNull
    private RoundStatus roundStatus;

    public Round(Player<T> player, LocalTime roundStart, R data) {
        this.player = player;
        this.roundStart = roundStart;
        this.data = data;
        this.roundStatus = new RoundStatus(RoundState.NEW);
    }
}
