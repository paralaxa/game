package sk.stopangin.game;

import lombok.Data;
import sk.stopangin.board.Board;
import sk.stopangin.entity.BaseIdentifiableEntity;
import sk.stopangin.field.Action;
import sk.stopangin.field.ActionField;
import sk.stopangin.field.Field;
import sk.stopangin.movement.Coordinates;
import sk.stopangin.movement.Movement;
import sk.stopangin.movement.MovementStatus;
import sk.stopangin.player.Player;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * Basic game class.
 *
 * @param <T> {@link sk.stopangin.movement.Coordinates} data
 * @param <R> {@link Round} data
 */
@Data
public abstract class Game<A extends Serializable, T extends Serializable, R> extends BaseIdentifiableEntity {
    private Board<T> board;
    private List<Player<T>> players;
    private boolean initialized;
    private Round<T, R> activeRound;

    protected  void preStartValidGame(Board<T> board, List<Player<T>> players, Player<T> activePlayer){}

    public final Round<T, R> startGame(Board<T> board, List<Player<T>> players, Player<T> activePlayer) {
        if (isValidConfiguration(players, board)) {
            preStartValidGame(board, players, activePlayer);
            this.board = board;
            this.players = players;
            initialized = true;
        }
        postStartGame(board, players, activePlayer);
        return doCreateNewRound(activePlayer);
    }

    protected  void postStartGame(Board<T> board, List<Player<T>> players, Player<T> activePlayer){}

    abstract boolean isValidConfiguration(List<Player<T>> players, Board<T> board);

    protected abstract Player<T> nextPlayer();

    protected final Round<T, R> createNexRound() {
        Round<T, R> newRound = doCreateNewRound(nextPlayer());
        newRound.setRoundStatus(new RoundStatus(RoundState.NEW));
        return newRound;
    }

    protected abstract Round<T, R> doCreateNewRound(Player<T> nextPlayer);

    public Round<T, R> commitRound(Movement<T> movement) {
        Movement<T> previousMovement = restorePreviousMovementForPiece(movement.getPieceId());
        MovementStatus movementStatus = activeRound.getPlayer().doMove(board, movement);
        if (isNextRoundBlockedForMovementStatus(movementStatus)) {
            activeRound.setRoundStatus(new RoundStatus(RoundState.IN_PROGRESS, movementStatus));
            activeRound.setActualPossition(Optional.of(previousMovement));
            return activeRound;
        }
        activeRound.setRoundEnd(LocalTime.now()); //todo maybe persist here, as the new line will do the cleanup.
        activeRound = createNexRound();
        activeRound.setActualPossition(Optional.of(movement));
        return activeRound;
    }

    private Movement<T> restorePreviousMovementForPiece(Long pieceId) {
        Long playersPieceId = activeRound.getPlayer().findOrFillPieceIdIfNull(pieceId);
        Coordinates<T> coordinatesForPieceId = board.getCoordinatesForPieceId(playersPieceId);
        Movement<T> movement = new Movement<>();
        movement.setPieceId(playersPieceId);
        movement.setNewPosition(coordinatesForPieceId);
        return movement;
    }

    private boolean isNextRoundBlockedForMovementStatus(MovementStatus movementStatus) {
        return !movementStatus.isAllowMovement();
    }

    public Optional<Action<A, T, R>> getActionForCurrentRound() {
        Optional<Movement<T>> actualPossition = activeRound.getActualPossition();
        Action<A, T, R> action = null;
        if (actualPossition.isPresent()) {
            Coordinates<T> currentRoundPosition = actualPossition.get().getNewPosition();
            Field<T> fieldForCoordinates = getBoard().getFieldForCoordinates(currentRoundPosition);
            if (fieldForCoordinates instanceof ActionField) {
                action = ((ActionField<A, T, R>) fieldForCoordinates).getAction();
            }
        }
        return Optional.ofNullable(action);
    }

}
