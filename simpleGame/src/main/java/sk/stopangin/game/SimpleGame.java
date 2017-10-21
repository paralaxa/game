package sk.stopangin.game;

import sk.stopangin.board.Board;
import sk.stopangin.board.SimpleBoard;
import sk.stopangin.player.Player;

import java.time.LocalTime;
import java.util.Set;

public class SimpleGame extends Game<Integer> {


    private CubeThrowRoundDataGenerator cubeThrowRoundDataGenerator = new CubeThrowRoundDataGenerator(6);

    @Override
    boolean isValidConfiguration(Set<Player> players, Board board) {
        return isSimpleBoard(board) && hasTwoOrMorePlayers(players);
    }

    private boolean hasTwoOrMorePlayers(Set<Player> players) {
        return players.size() >= 2;
    }

    private boolean isSimpleBoard(Board board) {
        return board instanceof SimpleBoard;
    }

    @Override
    Player setActivePlayer() {
        return null;
    }

    @Override
    Round<Integer> createNewRound() {
        return new Round<>(LocalTime.now(), cubeThrowRoundDataGenerator.generate());

    }
}
