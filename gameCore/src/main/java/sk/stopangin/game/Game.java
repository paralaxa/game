package sk.stopangin.game;

import lombok.Data;
import sk.stopangin.board.Board;
import sk.stopangin.entity.BaseIdentifiableEntity;
import sk.stopangin.movement.Movement;
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


    public final Round<T> createNexRound() {
        return doCreateNewRound(nextPlayer());
    }

    protected abstract Round<T> doCreateNewRound(Player nextPlayer);

    public Round<T> commitRound(Movement<T> movement) {
        activeRound.setMovement(movement);
        activeRound.setRoundEnd(LocalTime.now());
        activeRound.getPlayer().doMove(board, movement);
        activeRound.setRoundState(RoundState.FINISHED);
        return activeRound;
    }

}
