package sk.stopangin.game;

import sk.stopangin.board.Board;
import sk.stopangin.board.SimpleBoard;
import sk.stopangin.player.Player;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public class SimpleGame extends Game<Integer> {


    private CubeThrowRoundDataGenerator cubeThrowRoundDataGenerator = new CubeThrowRoundDataGenerator(6);

    @Override
    boolean isValidConfiguration(List<Player> players, Board board) {
        return isSimpleBoard(board) && hasTwoOrMorePlayers(players);
    }

    private boolean hasTwoOrMorePlayers(List<Player> players) {
        return players.size() >= 2;
    }

    private boolean isSimpleBoard(Board board) {
        return board instanceof SimpleBoard;
    }

    @Override
    protected Player nextPlayer() {
        int activePlayerIndex = getPlayers().indexOf(getActiveRound().getPlayer());
        int nextPlayerIndex = 0;
        if (activePlayerIndex != getPlayers().size() - 1) {
            nextPlayerIndex = activePlayerIndex + 1;
        }
        return getPlayers().get(nextPlayerIndex);
    }

    @Override
    protected Round<Integer> doCreateNewRound(Player player) {
        return new Round<>(nextPlayer(), LocalTime.now(), cubeThrowRoundDataGenerator.generate(), RoundState.NEW);
    }

}
