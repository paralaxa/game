package sk.stopangin.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.stopangin.board.Board;
import sk.stopangin.board.RandomSimpleGameBoardGenerator;
import sk.stopangin.board.SimpleBoard;
import sk.stopangin.field.Field;
import sk.stopangin.movement.Coordinates;
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

    private static final Logger log = LoggerFactory.getLogger(SimpleGame.class);

    private CubeThrowRoundDataGenerator cubeThrowRoundDataGenerator = new CubeThrowRoundDataGenerator(6);

    public Round<Integer> startGame(List<Player<Integer>> players) {
        enrichPlayersWithPieces(players);
        return super.startGame(RandomSimpleGameBoardGenerator.generate(), players, players.get(0));
    }

    private void enrichPlayersWithPieces(List<Player<Integer>> players) {
        long iter = 0;
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
    boolean isValidConfiguration(List<Player<Integer>> players, Board<Integer> board) {
        return isSimpleBoard(board) && hasTwoOrMorePlayers(players);
    }

    private boolean hasTwoOrMorePlayers(List<Player<Integer>> players) {
        return players.size() >= 2;
    }

    private boolean isSimpleBoard(Board<Integer> board) {
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
        movement.setNewPocition(new LinearCoordinates(getCurrentPlayesPiecePosition().getData() + getActiveRound().getData()));
        Round<Integer> integerRound = super.commitRound(movement);
        log.debug("Board after commit: " + getBoard());
        return integerRound;
    }

    //todo presunut do boardu (get position for field)
    private Coordinates<Integer> getCurrentPlayesPiecePosition() {
        Piece<Integer> currentPiece = getActiveRound().getPlayer().getPieces().iterator().next();
        Set<Field<Integer>> fields = getBoard().getFields();
        for (Field<Integer> field : fields) {
            if (field.getPiece() != null && field.getPiece().getId().equals(currentPiece.getId())) {
                return field.getPosition();
            }
        }
        return new Coordinates<>(0);
    }

    @Override
    protected Round<Integer> doCreateNewRound(Player<Integer> player) {
        Round<Integer> round = new Round<>(player, LocalTime.now(), cubeThrowRoundDataGenerator.generate());
        setActiveRound(round);
        return round;
    }

}
