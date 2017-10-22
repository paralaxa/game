package sk.stopangin.player;

import lombok.Data;
import sk.stopangin.board.Board;
import sk.stopangin.entity.BaseIdentifiableEntity;
import sk.stopangin.movement.Movement;
import sk.stopangin.movement.MovementStatus;
import sk.stopangin.piece.Piece;

import java.util.Set;

@Data
public abstract class Player<T> extends BaseIdentifiableEntity {
    private String name;
    private Set<Piece<T>> pieces;

    public MovementStatus doMove(Board<T> board, Movement<T> movement) {
        updateMovementWithMyPiece(movement);
        return doMovement(board, movement);
    }

    private void updateMovementWithMyPiece(Movement<T> movement) {
        Piece<T> currentMovementPiece = movement.getPiece();
        if (pieceNotSpecifiedForMovement(currentMovementPiece)) {
            if (hasOnlyOnePiece(movement)) {
                currentMovementPiece = pieces.iterator().next();
            } else {
                throw new PlayerExcpetion("Piece not specified for movement");
            }
        }
        movement.setPiece(getMyPieceForId(currentMovementPiece.getId()));
    }

    private boolean pieceNotSpecifiedForMovement(Piece<T> currentMovementPiece) {
        return currentMovementPiece== null;
    }

    private boolean hasOnlyOnePiece(Movement<T> movement) {
        return pieces.size() == 1;
    }


    private Piece<T> getMyPieceForId(Long pieceId) {
        for (Piece myPiece : pieces) {
            if (myPiece.getId().equals(pieceId)) {
                return myPiece;
            }
        }
        throw new PlayerExcpetion("No piece with id:" + pieceId + " for player:" + name);
    }

    protected abstract MovementStatus doMovement(Board<T> board, Movement<T> movement);


}
