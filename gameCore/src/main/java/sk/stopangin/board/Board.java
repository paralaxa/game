package sk.stopangin.board;

import lombok.Builder;
import sk.stopangin.field.Field;
import sk.stopangin.movement.Movement;
import sk.stopangin.movement.MovementStatus;

import java.util.Set;

@Builder
public abstract class Board {
    private Set<Field> fields;

    public abstract void initialize(Set<Field> fields);

    public MovementStatus updateBasedOnMovement(Movement movement) {
        if (isMoveOutOfBoundaries(movement)) {
            return MovementStatus.OUT_OF_BOUNDARIES;
        }
        if (isMovementCollision(movement)) {
            return MovementStatus.COLLISION;
        }
        return MovementStatus.DONE;
    }

    protected abstract boolean isMoveOutOfBoundaries(Movement movement);

    protected abstract boolean isMovementCollision(Movement movement);
}
