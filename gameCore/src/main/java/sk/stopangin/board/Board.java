package sk.stopangin.board;

import lombok.Data;
import sk.stopangin.field.Field;
import sk.stopangin.movement.Coordinates;
import sk.stopangin.movement.Movement;
import sk.stopangin.movement.MovementStatus;
import sk.stopangin.piece.Piece;

import java.io.Serializable;
import java.util.Set;

@Data
//todo define boolean for collision , so collision behaviour can be controlled via flag and multiple pieces can be allowed on one field, when collisionAllowed=true;
public abstract class Board<T extends Serializable> {
    private Set<Field<T>> fields;

    public Board(Set<Field<T>> fields) {
        this.fields = fields;
    }

    public MovementStatus updateBasedOnMovement(Movement<T> movement) {
         Long currentMovementPieceId = movement.getPieceId();
        Piece<T> currentMovementPiece = null;
        Field<T> currentMovementField = null, newMovementField = null;
        for (Field<T> field : fields) {
            if (isCurrentMovementsPieceOnField(currentMovementPieceId, field)) {
                currentMovementPiece = field.getPiece();
                currentMovementField = field;
            }
            if (field.getPosition().equals(movement.getNewPosition())) {
                newMovementField = field;
            }
            if (isEverythingSetup(currentMovementPiece, currentMovementField, newMovementField)) {
                break;
            }
        }
        return movementStatusForCurrentMovement(movement, currentMovementPiece, currentMovementField, newMovementField);
    }

    private MovementStatus movementStatusForCurrentMovement(Movement<T> movement, Piece<T> currentMovementPiece, Field<T> currentMovementField, Field<T> newMovementField) {
        if (isEverythingSetup(currentMovementPiece, currentMovementField, newMovementField)) {
            if (currentMovementPiece.isValidMove(currentMovementField.getPosition(), movement.getNewPosition())) {
                if (isMovementCollision(movement)) {
                    return MovementStatus.COLLISION;
                }
                removePieceFromField(currentMovementField);
                putPieceOnNewField(movement, newMovementField, currentMovementPiece);
                return MovementStatus.DONE;
            } else {
                return MovementStatus.INVALID_POSITION;
            }
        }
        return MovementStatus.NON_EXISTING_FIELD;
    }

    private boolean isEverythingSetup(Piece<T> currentMovementPiece, Field<T> currentMovementField, Field<T> newMovementField) {
        return currentMovementField != null && newMovementField != null && currentMovementPiece != null;
    }

    public Field<T> getFieldForCoordinates(Coordinates<T> coordinates) {
        return fields.stream()
                .filter(tField -> tField.getPosition().equals(coordinates))
                .findFirst()
                .orElse(null);
    }

    protected Piece<T> getPieceForCoordinates(Coordinates<T> coordinates) {
        return getFieldForCoordinates(coordinates).getPiece();
    }


    private boolean isCurrentMovementsPieceOnField(Long currentMovementPieceId, Field<T> field) {
        return field.getPiece() != null && field.getPiece().getId().equals(currentMovementPieceId);
    }

    private void putPieceOnNewField(Movement<T> movement, Field<T> field, Piece<T> currentMovementPiece) {
        if (field.getPosition().equals(movement.getNewPosition())) {
            field.setPiece(currentMovementPiece);
        }
    }

    private void removePieceFromField(Field<T> field) {
        field.setPiece(null);
    }


    protected boolean isMovementCollision(Movement<T> movement) {
        return getPieceForCoordinates(movement.getNewPosition()) != null;
    }

    public abstract Coordinates<T> getCoordinatesForPieceId(Long pieceId);
}
