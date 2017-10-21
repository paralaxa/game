package sk.stopangin.player;

import lombok.Data;
import sk.stopangin.entity.BaseIdentifiableEntity;
import sk.stopangin.movement.Movement;
import sk.stopangin.piece.Piece;

import java.util.Set;

@Data
public abstract class Player extends BaseIdentifiableEntity {
    private String name;
    private Set<Piece> pieces;

    public void doMove(Movement movement) {
        canMovePiece(movement);
    }

    private void canMovePiece(Movement movement) {
        if (pieces.contains(movement.getPiece())) {
            doMovement(movement);
        }
    }

    public abstract void doMovement(Movement movement);


}
