package sk.stopangin.game;

import lombok.Data;
import sk.stopangin.board.Board;
import sk.stopangin.entity.BaseIdentifiableEntity;
import sk.stopangin.player.Player;

import java.io.Serializable;
import java.util.List;

@Data
public abstract class Game<T extends Serializable> extends BaseIdentifiableEntity {
    private Board board;
    private List<Player> players;
    private boolean initialized;
    private Round<T> activeRound;

    public Round startGame(Board board, List<Player> players, Player activePlayer) {
        if (isValidConfiguration(players, board)) {
            this.board = board;
            this.players = players;
            initialized = true;
        }
        return doCreateNewRound(nextPlayer());
    }

    abstract boolean isValidConfiguration(List<Player> players, Board board);

    protected abstract Player nextPlayer();


    public final Round<T> createNexRound() {
        return doCreateNewRound(nextPlayer());
    }

    protected abstract Round<T> doCreateNewRound(Player nextPlayer);

    public void commitRound() {
        activeRound.getPlayer().doMove(board, activeRound.getMovement());
    }

}
