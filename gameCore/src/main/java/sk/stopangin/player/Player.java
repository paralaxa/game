package sk.stopangin.player;

import lombok.Data;
import sk.stopangin.board.Board;
import sk.stopangin.entity.BaseIdentifiableEntity;
import sk.stopangin.movement.Movement;
import sk.stopangin.movement.MovementStatus;
import sk.stopangin.piece.Piece;

import java.util.Set;

@Data
public abstract class Player extends BaseIdentifiableEntity {
    private String name;
    private Set<Piece> pieces;

    public MovementStatus doMove(Board board, Movement movement) {
        updateMovementWithMyPiece(movement);
        return doMovement(board, movement);
    }

    private void updateMovementWithMyPiece(Movement movement) {
        movement.setPiece(getMyPieceForId(movement.getPiece().getId()));
    }


    private Piece getMyPieceForId(Long pieceId) {
        for (Piece myPiece : pieces) {
            if (myPiece.getId().equals(pieceId)) {
                return myPiece;
            }
        }
        throw new PlayerExcpetion("No piece with id:" + pieceId + " for player" + name);
    }

    protected abstract MovementStatus doMovement(Board board, Movement movement);


}
