package sk.stopangin.piece;

import sk.stopangin.movement.Coordinates;
import sk.stopangin.movement.MovementType;

import java.util.Set;

public class LinearMovingPiece extends Piece {


    public LinearMovingPiece() {
    }

    public LinearMovingPiece(String name, Coordinates actualPosition, Set<MovementType> movementTypes) {
        super(name, actualPosition, movementTypes);
    }
}
