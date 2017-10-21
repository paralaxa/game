package sk.stopangin.piece;

import lombok.Data;
import sk.stopangin.entity.BaseIdentifiableEntity;
import sk.stopangin.movement.Coordinates;
import sk.stopangin.movement.MovementType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
public abstract class Piece extends BaseIdentifiableEntity {
    private String name;
    private Coordinates actualPosition;
    private Set<MovementType> movementTypes;

    public boolean isValidMove(Coordinates coordinates) {
        MovementType movementType = identifyMovementTypeBasedOnNewCoordinates(coordinates);
        return movementTypes.contains(movementType);
    }

    protected MovementType identifyMovementTypeBasedOnNewCoordinates(Coordinates newCoordinates) {
        Map<Coordinates, MovementType> allPossibleCoordinatesWithMovementTypes = calculateAllPossibleCoordinatesWithMovementTypes();
        if (allPossibleCoordinatesWithMovementTypes.containsKey(newCoordinates)) {
            return allPossibleCoordinatesWithMovementTypes.get(newCoordinates);
        }

        throw new PieceMovementException("Coordinates:" + newCoordinates + "are illegal for piece:" + getId() + "[" + name + "]");
    }

    private Map<Coordinates, MovementType> calculateAllPossibleCoordinatesWithMovementTypes() {
        Map<Coordinates, MovementType> coordinatesMovementTypeMap = new HashMap<>();
        for (MovementType movementType : movementTypes) {
            movementType.apply(actualPosition);
            coordinatesMovementTypeMap.put(, movementType);
        }
        return coordinatesMovementTypeMap;
    }

}
