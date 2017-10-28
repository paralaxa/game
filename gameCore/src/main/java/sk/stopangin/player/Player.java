package sk.stopangin.player;

import lombok.Data;
import sk.stopangin.board.Board;
import sk.stopangin.entity.BaseIdentifiableEntity;
import sk.stopangin.movement.Movement;
import sk.stopangin.movement.MovementStatus;
import sk.stopangin.piece.Piece;

import java.io.Serializable;
import java.util.Set;

@Data
public abstract class Player<T extends Serializable> extends BaseIdentifiableEntity {
    private String name;
    private Set<Piece<T>> pieces;
    private int score;

    public MovementStatus doMove(Board<T> board, Movement<T> movement) {
        updateMovementWithMyPiece(movement);
        return doMovement(board, movement);
    }

    private void updateMovementWithMyPiece(Movement<T> movement) {
        Long currentMovementPieceId = movement.getPieceId();
        if (pieceNotSpecifiedForMovement(currentMovementPieceId) && hasOnlyOnePiece()) {
            currentMovementPieceId = getPlayersOnlyPiece().getId();
        } else {
            throw new PlayerException("Piece not specified for movement");
        }
        if (hasPieceWithId(currentMovementPieceId)) {
            movement.setPieceId(currentMovementPieceId);
        } else {
            throw new PlayerException("No piece with id:" + currentMovementPieceId + " for player:" + name);
        }
    }

    private Piece<T> getPlayersOnlyPiece() {
        return pieces.iterator().next();
    }

    private boolean pieceNotSpecifiedForMovement(Long currentMovementPieceId) {
        return currentMovementPieceId == null;
    }

    private boolean hasOnlyOnePiece() {
        return pieces.size() == 1;
    }


    private boolean hasPieceWithId(Long pieceId) {
        for (Piece<T> myPiece : pieces) {
            if (myPiece.getId().equals(pieceId)) {
                return true;
            }
        }
        return false;
    }

    protected abstract MovementStatus doMovement(Board<T> board, Movement<T> movement);
}
