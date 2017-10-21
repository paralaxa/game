package sk.stopangin.movement;


public class LinearMovementType implements MovementType {
    @Override
    public void move(Movement movement) {
        Coordinates newPocition = movement.getNewPocition();
        movement.getPiece().setActualPosition(newPocition);
    }
}
