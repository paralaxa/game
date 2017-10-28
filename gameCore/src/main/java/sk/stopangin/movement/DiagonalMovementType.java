package sk.stopangin.movement;

public class DiagonalMovementType implements MovementType<TwoDimensionalCoordinatesData> {
    private int[][] movementMatrix = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};


    @Override
    public boolean isMatch(Coordinates<TwoDimensionalCoordinatesData> currentCoordinates, Coordinates<TwoDimensionalCoordinatesData> newCoordinates) {
        TwoDimensionalCoordinatesData currentCoordinatesData = currentCoordinates.getData();
        TwoDimensionalCoordinatesData newCoordinatesData = newCoordinates.getData();
        for (int[] matrix : movementMatrix) {
            if (newCoordinatesData.getX() == currentCoordinatesData.getX() + matrix[0] && newCoordinatesData.getY() == currentCoordinatesData.getY() + matrix[1]) {
                return true;
            }
        }
        return false;
    }
}
