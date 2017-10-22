package sk.stopangin.piece;

import sk.stopangin.movement.LinearMovementType;
import sk.stopangin.movement.MovementType;

import java.util.HashSet;
import java.util.Set;

public class LinearMovingPiece extends Piece<Integer> {


    public LinearMovingPiece(String name) {
        super(name, new LinearMovementType());
    }

}
