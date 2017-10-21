package sk.stopangin.piece;

import lombok.AllArgsConstructor;
import lombok.Data;
import sk.stopangin.entity.BaseIdentifiableEntity;
import sk.stopangin.movement.Coordinates;
import sk.stopangin.movement.MovementType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
public abstract class Piece extends BaseIdentifiableEntity {
    private String name;
    private Coordinates actualPosition;
    private Set<MovementType> movementTypes;

    public Piece() {
    }

    public boolean isValidMove(Coordinates coordinates) {
        for (MovementType movementType : movementTypes) {
            if (movementType.isMatch(actualPosition, coordinates)) {
                return true;
            }
        }
        return false;
    }
}
