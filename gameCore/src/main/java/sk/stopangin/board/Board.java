package sk.stopangin.board;

import sk.stopangin.field.Field;
import sk.stopangin.movement.Coordinates;
import sk.stopangin.movement.Movement;
import sk.stopangin.movement.MovementStatus;
import sk.stopangin.movement.MovementType;
import sk.stopangin.piece.Piece;

import java.util.Set;


public abstract class Board<T> {
    private Set<Field<T>> fields;

    public Board(Set<Field<T>> fields) {
        this.fields = fields;
    }

    public MovementStatus updateBasedOnMovement(Movement movement) {
        if (isMoveOutOfBoundaries(movement)) {
            return MovementStatus.OUT_OF_BOUNDARIES;
        }
        if (isMovementCollision(movement)) {
            return MovementStatus.COLLISION;
        }
        return updateBoard(movement);
    }

    private MovementStatus updateBoard(Movement movement) {
        for (Field<T> field : fields) {
            Piece currentMovementPiece = movement.getPiece();
            if (isPieceOnField(field, currentMovementPiece)) {
                if (isValidMove(movement, field.getPosition(), movement.getNewPocition())) {
                    removePieceFromPield(field);
                } else {
                    return MovementStatus.INVALID_POSITION;
                }
            }
            putPieceOnNewField(movement, field, currentMovementPiece);
        }
        return MovementStatus.DONE;
    }

    private boolean isPieceOnField(Field field, Piece currentMovementPiece) {
        return field.getPiece() != null && field.getPiece().getId().equals(currentMovementPiece.getId());
    }

    private void putPieceOnNewField(Movement movement, Field field, Piece currentMovementPiece) {
        if (field.getPosition().equals(movement.getNewPocition())) {
            field.setPiece(currentMovementPiece);
        }
    }

    private void removePieceFromPield(Field field) {
        field.setPiece(null);
    }

    protected boolean isEmpty() { //todo implement
        for (Field field : fields) {
            if (field.getPiece() != null) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidMove(Movement<T> movement, Coordinates actualPosition, Coordinates newCoordinates) {
        for (MovementType movementType : movement.getPiece().getMovementTypes()) {
            if (movementType.isMatch(actualPosition, newCoordinates)) {
                return true;
            }
        }
        return false;
    }

    protected abstract boolean isMoveOutOfBoundaries(Movement movement);

    protected abstract boolean isMovementCollision(Movement movement);
}
