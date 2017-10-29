package sk.stopangin.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Set<Piece<T>> pieces;
    private int score;

    public MovementStatus doMove(Board<T> board, Movement<T> movement) {
        updateMovementWithMyPiece(movement);
        return doMovement(board, movement);
    }

    /**
     * If id is null and there is only one piece for player, this method return the only pieces id,
     * otherwise finds the piece for given id and return it
     *
     * @param pieceId piece id to be found, or null, if there is only one piece for player
     * @return valid piece id for this player
     */
    public Long findOrFillPieceIdIfNull(Long pieceId) {
        if (pieceNotSpecifiedForMovement(pieceId) && hasOnlyOnePiece()) {
            return getPlayersOnlyPiece().getId();
        }
        if (hasPieceWithId(pieceId)) {
            return pieceId;
        } else {
            throw new PlayerException("No piece with id:" + pieceId + " for player:" + name);
        }
    }

    private void updateMovementWithMyPiece(Movement<T> movement) {
        Long currentMovementPieceId = movement.getPieceId();
        movement.setPieceId(findOrFillPieceIdIfNull(currentMovementPieceId));
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
