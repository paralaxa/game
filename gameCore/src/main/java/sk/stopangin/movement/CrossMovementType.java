package sk.stopangin.movement;

public class CrossMovementType implements MovementType<TwoDimensionalCoordinatesData>{
    @Override
    public boolean isMatch(Coordinates<TwoDimensionalCoordinatesData> currentCoordinates, Coordinates<TwoDimensionalCoordinatesData> newCoordinates) {
        return false;
    }
}
