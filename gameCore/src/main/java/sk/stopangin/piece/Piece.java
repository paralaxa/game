package sk.stopangin.piece;

import lombok.Data;
import sk.stopangin.entity.BaseIdentifiableEntity;
import sk.stopangin.movement.Coordinates;
import sk.stopangin.movement.MovementType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Data
public abstract class Piece<T extends Serializable> extends BaseIdentifiableEntity {
    private String name;
    private Set<MovementType<T>> movementTypes = new HashSet<>();

    public Piece(String name, MovementType<T>... varMovementTypes) {
        this.name = name;
        movementTypes.addAll(Arrays.asList(varMovementTypes));
    }

    public boolean isValidMove(Coordinates<T> actualPosition, Coordinates<T> newCoordinates) {
        for (MovementType movementType : movementTypes) {
            if (movementType.isMatch(actualPosition, newCoordinates)) {
                return true;
            }
        }
        return false;
    }
}
