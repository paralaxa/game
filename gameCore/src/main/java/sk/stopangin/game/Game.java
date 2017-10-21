package sk.stopangin.game;

import sk.stopangin.board.Board;
import sk.stopangin.entity.BaseIdentifiableEntity;
import sk.stopangin.player.Player;

import java.io.Serializable;
import java.util.Set;

public abstract class Game<T extends Serializable> extends BaseIdentifiableEntity {
    private Board board;
    private Set<Player> players;
    private Player activePlayer;
    private boolean initialized;

    public void initialize(Board board, Set<Player> players, Player activePlayer) {
        if (isValidConfiguration(players, board)) {
            setActivePlayer();
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

    abstract boolean isValidConfiguration(Set<Player> players, Board board);

    abstract Player setActivePlayer();

    private boolean isActivePlayerValid() {
        return players.contains(activePlayer);
    }

    abstract Round<T> createNewRound();

}
