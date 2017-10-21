package sk.stopangin.game;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import sk.stopangin.movement.Movement;
import sk.stopangin.player.Player;

import java.io.Serializable;
import java.time.LocalTime;

@Data
@RequiredArgsConstructor
public class Round<T extends Serializable> {
    private Movement movement;
    @NonNull
    private final Player player;
    @NonNull
    private final LocalTime roundStart;
    private LocalTime roundEnd;
    @NonNull
    private final T data;
    @NonNull
    private RoundState roundState;
}
