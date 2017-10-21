package sk.stopangin.player;

import lombok.Data;
import sk.stopangin.board.Board;
import sk.stopangin.entity.BaseIdentifiableEntity;
import sk.stopangin.movement.Movement;
import sk.stopangin.piece.Piece;

import java.util.Set;

@Data
public abstract class Player extends BaseIdentifiableEntity {
    private String name;
    private Set<Piece> pieces;

    public void doMove(Board board, Movement movement) {
        canMovePiece(board, movement);
    }

    private void canMovePiece(Board board, Movement movement) {
        if (isThisMinePiece(movement.getPiece())) {
            doMovement(board, movement);
        }
    }

    private boolean isThisMinePiece(Piece piece) {
        return pieces.contains(piece);
    }

    protected abstract void doMovement(Board board, Movement movement);


}
