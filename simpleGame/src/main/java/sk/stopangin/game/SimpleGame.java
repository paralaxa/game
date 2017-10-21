package sk.stopangin.game;

import sk.stopangin.board.Board;
import sk.stopangin.player.Player;

import java.time.LocalTime;
import java.util.Set;

public class SimpleGame extends Game<Integer> {


    private CubeThrowRoundDataGenerator cubeThrowRoundDataGenerator = new CubeThrowRoundDataGenerator(6);

    @Override
    boolean isValidConfigration(Set<Player> players, Board board) {
        return false;
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
