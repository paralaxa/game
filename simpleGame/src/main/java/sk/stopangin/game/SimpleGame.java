package sk.stopangin.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.stopangin.board.Board;
import sk.stopangin.board.SimpleBoard;
import sk.stopangin.field.Field;
import sk.stopangin.movement.TwoDimensionalCoordinates;
import sk.stopangin.movement.TwoDimensionalCoordinatesData;
import sk.stopangin.piece.AnyDirectionTwoDimensionalMovingPiece;
import sk.stopangin.piece.Piece;
import sk.stopangin.player.Player;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SimpleGame extends Game<TwoDimensionalCoordinatesData, Void> {

    private static final Logger log = LoggerFactory.getLogger(SimpleGame.class);

    @Override
    boolean isValidConfiguration(List<Player<TwoDimensionalCoordinatesData>> players, Board<TwoDimensionalCoordinatesData> board) {
        return board instanceof SimpleBoard && !players.isEmpty();
    }

    @Override
    protected Round<TwoDimensionalCoordinatesData, Void> doCreateNewRound(Player<TwoDimensionalCoordinatesData> nextPlayer) {
        Round<TwoDimensionalCoordinatesData, Void> round = new Round<>(nextPlayer, LocalTime.now(), null);
        setActiveRound(round);
        return round;
    }

    public Round<TwoDimensionalCoordinatesData, Void> startGame(SimpleBoard simpleBoard, List<Player<TwoDimensionalCoordinatesData>> players, Player<TwoDimensionalCoordinatesData> activePlayer) {
        if (isValidConfiguration(players, simpleBoard)) {
            Round<TwoDimensionalCoordinatesData, Void> round = super.startGame(simpleBoard, players, activePlayer);
            enrichPlayersWithPieces(players);
            return round;
        }
        throw new GameException("Not a valid configuration, either players are not defined or board is not of a type simple board");
    }

    public Round<TwoDimensionalCoordinatesData, Void> startGame(SimpleBoard simpleBoard, List<Player<TwoDimensionalCoordinatesData>> players) {
        return this.startGame(simpleBoard, players, players.get(0));
    }

    private void enrichPlayersWithPieces(List<Player<TwoDimensionalCoordinatesData>> players) {
        long iter = 0;
        for (Player player : players) {
            player.setId(iter);
            Piece piece = new AnyDirectionTwoDimensionalMovingPiece(player.getId(), player.getName() + "_piece");
            piece.setId(player.getId());
            Set<Piece> pieces1 = new HashSet<>();
            pieces1.add(piece);
            player.setPieces(pieces1);
            Field<TwoDimensionalCoordinatesData> beginningField = getBoard().getFieldForCoordinates(new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(1, 1)));
            beginningField.setPiece(piece);
            iter++;
        }
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
}
