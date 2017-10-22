package sk.stopangin.game;

import sk.stopangin.board.Board;
import sk.stopangin.board.RandomSimpleGameBoardGenerator;
import sk.stopangin.board.SimpleBoard;
import sk.stopangin.movement.LinearCoordinates;
import sk.stopangin.movement.Movement;
import sk.stopangin.piece.LinearMovingPiece;
import sk.stopangin.piece.Piece;
import sk.stopangin.player.Player;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SimpleGame extends Game<Integer> {


    private CubeThrowRoundDataGenerator cubeThrowRoundDataGenerator = new CubeThrowRoundDataGenerator(6);

    public Round<Integer> startGame(List<Player> players) {
        enrichPlayersWithPieces(players);
        return super.startGame(RandomSimpleGameBoardGenerator.generate(), players, players.get(0));
    }

    private void enrichPlayersWithPieces(List<Player> players) {
        long iter=0;
        for (Player player : players) {
            player.setId(iter);
            Piece piece = new LinearMovingPiece(player.getName() + "_piece");
            piece.setId(player.getId());
            Set<Piece> pieces1 = new HashSet<>();
            pieces1.add(piece);
            player.setPieces(pieces1);
            iter++;
        }
    }

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
    public Round<Integer> commitRound(Movement<Integer> movement) {
        movement.setNewPocition(new LinearCoordinates(getActiveRound().getData()));
        return super.commitRound(movement);
    }

    @Override
    protected Round<Integer> doCreateNewRound(Player player) {
        Round<Integer> round = new Round<>(player, LocalTime.now(), cubeThrowRoundDataGenerator.generate());
        setActiveRound(round);
        return round;
    }

}
