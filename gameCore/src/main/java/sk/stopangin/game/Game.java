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
    private Player activePlayer;
    private boolean initialized;

    public void initialize(Board board, List<Player> players, Player activePlayer) {
        if (isValidConfiguration(players, board)) {
            nextPlayer();
            if (isActivePlayerValid()) {
                this.board = board;
                this.players = players;
                this.activePlayer = activePlayer;
                initialized = true;
            }
        }
    }

    public void startGame() {
        if (!initialized) {
            throw new GameException("Game not initialized");
        }
    }

    abstract boolean isValidConfiguration(List<Player> players, Board board);

    protected abstract Player nextPlayer();

    private boolean isActivePlayerValid() {
        return players.contains(activePlayer);
    }

    protected final Round<T> createNexRound() {
        setActivePlayer(nextPlayer());
        return doCreateNewRound();
    }

    protected abstract Round<T> doCreateNewRound();


}
