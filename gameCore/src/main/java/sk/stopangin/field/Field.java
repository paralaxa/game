package sk.stopangin.field;

import lombok.Data;
import sk.stopangin.entity.BaseIdentifiableEntity;
import sk.stopangin.movement.Coordinates;
import sk.stopangin.piece.Piece;

@Data
public abstract class Field<T> extends BaseIdentifiableEntity{
    private Coordinates<T> position;
    private Piece piece;

}
