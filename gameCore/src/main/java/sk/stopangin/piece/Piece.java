package sk.stopangin.piece;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.stopangin.entity.BaseIdentifiableEntity;
import sk.stopangin.movement.Coordinates;
import sk.stopangin.movement.MovementType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Piece<T extends Serializable> extends BaseIdentifiableEntity {

    private String name;
    private Set<MovementType<T>> movementTypes = new HashSet<>();

    public Piece(Long id, String name, MovementType<T>... varMovementTypes) {
        setId(id);
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
