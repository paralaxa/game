package sk.stopangin.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.stopangin.board.Board;
import sk.stopangin.board.ChessboardGenerator;
import sk.stopangin.board.SimpleBoard;
import sk.stopangin.movement.*;
import sk.stopangin.piece.LinearMovingPiece;
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
        return board instanceof SimpleBoard && players.size() > 0;
    }

    @Override
    protected Round<TwoDimensionalCoordinatesData, Void> doCreateNewRound(Player<TwoDimensionalCoordinatesData> nextPlayer) {
        Round<TwoDimensionalCoordinatesData, Void> round = new Round<>(nextPlayer, LocalTime.now(), null);
        setActiveRound(round);
        return round;
    }

    public Round<TwoDimensionalCoordinatesData, Void> startGame(List<Player<TwoDimensionalCoordinatesData>> players) {
        enrichPlayersWithPieces(players);
        return super.startGame(new SimpleBoard(ChessboardGenerator.generateFieldsForChessboard()), players, players.get(0));
    }

    private void enrichPlayersWithPieces(List<Player<TwoDimensionalCoordinatesData>> players) {
        long iter = 0;
        for (Player player : players) {
            player.setId(iter);
            Piece piece = new LinearMovingPiece(player.getId(), player.getName() + "_piece");
            piece.setId(player.getId());
            Set<Piece> pieces1 = new HashSet<>();
            pieces1.add(piece);
            player.setPieces(pieces1);
            iter++;
        }
    }


    private boolean hasTwoOrMorePlayers(List<Player<TwoDimensionalCoordinatesData>> players) {
        return players.size() >= 2;
    }

    private boolean isSimpleBoard(Board<TwoDimensionalCoordinatesData> board) {
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
}
