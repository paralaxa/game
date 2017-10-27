package sk.stopangin.movement;

public class DiagonalMovementType implements MovementType<TwoDimensionalCoordinatesData> {
    @Override
    public boolean isMatch(Coordinates<TwoDimensionalCoordinatesData> currentCoordinates, Coordinates<TwoDimensionalCoordinatesData> newCoordinates) {
        TwoDimensionalCoordinatesData currentCoordinatesData = currentCoordinates.getData();
        TwoDimensionalCoordinatesData newCoordinatesData = newCoordinates.getData();
        return currentCoordinatesData.getY() != newCoordinatesData.getY() && currentCoordinatesData.getX() != newCoordinatesData.getX();
    }
}
