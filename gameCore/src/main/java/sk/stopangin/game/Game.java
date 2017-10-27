package sk.stopangin.game;

import lombok.Data;
import sk.stopangin.board.Board;
import sk.stopangin.entity.BaseIdentifiableEntity;
import sk.stopangin.movement.Movement;
import sk.stopangin.movement.MovementStatus;
import sk.stopangin.player.Player;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

/**
 * Basic game class.
 *
 * @param <T>  {@link sk.stopangin.movement.Coordinates} data
 * @param <R>  {@link Round} data
 */
@Data
public abstract class Game<T extends Serializable,R> extends BaseIdentifiableEntity {
    private Board<T> board;
    private List<Player<T>> players;
    private boolean initialized;
    private Round<T, R> activeRound;

    public Round<T, R> startGame(Board<T> board, List<Player<T>> players, Player<T> activePlayer) {
        if (isValidConfiguration(players, board)) {
            this.board = board;
            this.players = players;
            initialized = true;
        }
        return doCreateNewRound(activePlayer);
    }

    abstract boolean isValidConfiguration(List<Player<T>> players, Board<T> board);

    protected abstract Player<T> nextPlayer();

    protected final Round<T, R> createNexRound() {
        Round<T, R> newRound = doCreateNewRound(nextPlayer());
        newRound.setRoundStatus(new RoundStatus(RoundState.NEW));
        return newRound;
    }

    protected abstract Round<T, R> doCreateNewRound(Player<T> nextPlayer);

    public Round<T, R> commitRound(Movement<T> movement) {
        activeRound.setMovement(movement);
        MovementStatus movementStatus = activeRound.getPlayer().doMove(board, movement);
        if (!MovementStatus.DONE.equals(movementStatus)) {
            activeRound.setRoundStatus(new RoundStatus(RoundState.IN_PROGRESS, movementStatus));
            return activeRound;
        }
        activeRound.setRoundEnd(LocalTime.now());
        activeRound = createNexRound();
        return activeRound;
    }

}
