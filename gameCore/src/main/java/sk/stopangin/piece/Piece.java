package sk.stopangin.piece;

import lombok.Data;
import sk.stopangin.entity.BaseIdentifiableEntity;
import sk.stopangin.movement.MovementType;

import java.util.HashSet;
import java.util.Set;

@Data
public abstract class Piece<T> extends BaseIdentifiableEntity {
    private String name;
    private Set<MovementType<T>> movementTypes = new HashSet<>();

    public Piece(String name, MovementType<T>... varMovementTypes) {
        this.name = name;
        for (MovementType<T> varMovementType : varMovementTypes) {
            movementTypes.add(varMovementType);
        }
    }
}
