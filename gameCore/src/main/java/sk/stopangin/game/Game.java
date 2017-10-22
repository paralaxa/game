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

@Data
public abstract class Game<T extends Serializable> extends BaseIdentifiableEntity {
    private Board board;
    private List<Player> players;
    private boolean initialized;
    private Round<T> activeRound;

    public Round<T> startGame(Board board, List<Player> players, Player activePlayer) {
        if (isValidConfiguration(players, board)) {
            this.board = board;
            this.players = players;
            initialized = true;
        }
        return doCreateNewRound(activePlayer);
    }

    abstract boolean isValidConfiguration(List<Player> players, Board board);

    protected abstract Player nextPlayer();

    protected final Round<T> createNexRound() {
        Round<T> newRound = doCreateNewRound(nextPlayer());
        newRound.setRoundStatus(new RoundStatus(RoundState.NEW));
        return newRound;
    }

    protected abstract Round<T> doCreateNewRound(Player nextPlayer);

    public Round<T> commitRound(Movement<T> movement) {
        activeRound.setMovement(movement);
        MovementStatus movementStatus = activeRound.getPlayer().doMove(board, movement);
        if (!MovementStatus.DONE.equals(movementStatus)) {
            activeRound.setRoundStatus(new RoundStatus(RoundState.IN_PROGRESS, movementStatus));
            return activeRound;
        }
        activeRound.setRoundEnd(LocalTime.now());
        activeRound = createNexRound();//todo commit active round into repository
        return activeRound;
    }

}
