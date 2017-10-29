package sk.stopangin.board;

import lombok.Data;
import sk.stopangin.field.ActionData;
import sk.stopangin.field.ActionField;
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
        Field<T> currentMovementField = null;
        Field<T> newMovementField = null;
        for (Field<T> field : fields) {
            if (isCurrentMovementsPieceOnField(currentMovementPieceId, field)) {
                currentMovementPiece = field.getPiece();
                currentMovementField = field;
            }
            if (isFieldNewMovementsField(movement, field)) {
                newMovementField = field;
            }
            if (isEverythingSetup(currentMovementPiece, currentMovementField, newMovementField)) {
                break;
            }
        }
        return movementStatusForCurrentMovement(movement, currentMovementPiece, currentMovementField, newMovementField);
    }

    private boolean isFieldNewMovementsField(Movement<T> movement, Field<T> field) {
        return field.getPosition().equals(movement.getNewPosition());
    }

    private MovementStatus movementStatusForCurrentMovement(Movement<T> movement, Piece<T> currentMovementPiece, Field<T> currentMovementField, Field<T> newMovementField) {
        if (isEverythingSetup(currentMovementPiece, currentMovementField, newMovementField)) {
            if (currentMovementPiece.isValidMove(currentMovementField.getPosition(), movement.getNewPosition())) {
                if (isMovementCollision(movement)) {
                    return MovementStatus.COLLISION;
                }
                MovementStatus movementStatus = movementStatusBasedOnNewMovementFieldType(newMovementField);
                if (MovementStatus.DONE.equals(movementStatus)) {
                    removePieceFromField(currentMovementField);
                    putPieceOnNewField(newMovementField, currentMovementPiece);
                }
                return movementStatus;
            } else {
                return MovementStatus.INVALID_POSITION;
            }
        }
        return MovementStatus.NON_EXISTING_FIELD;
    }

    private MovementStatus movementStatusBasedOnNewMovementFieldType(Field<T> newMovementField) {
        if (newMovementField instanceof ActionField) {
            ActionData actionData = ((ActionField) newMovementField).getAction().getActionData();
            if (!actionData.isUsed()) {
                if (actionData.isBlocking()) {
                    return MovementStatus.ACTION_REQUIRED;
                } else {
                    return MovementStatus.ACTION_POSSIBLE;
                }
            }
        }
        return MovementStatus.DONE;
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

    private void putPieceOnNewField(Field<T> field, Piece<T> currentMovementPiece) {
            field.setPiece(currentMovementPiece);
    }

    private void removePieceFromField(Field<T> field) {
        field.setPiece(null);
    }


    protected boolean isMovementCollision(Movement<T> movement) {
        return getPieceForCoordinates(movement.getNewPosition()) != null;
    }

    public abstract Coordinates<T> getCoordinatesForPieceId(Long pieceId);
}
