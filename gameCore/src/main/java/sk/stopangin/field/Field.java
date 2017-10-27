package sk.stopangin.field;

import lombok.Data;
import sk.stopangin.entity.BaseIdentifiableEntity;
import sk.stopangin.movement.Coordinates;
import sk.stopangin.piece.Piece;

import java.io.Serializable;

@Data
public abstract class Field<T extends Serializable> extends BaseIdentifiableEntity{
    private Coordinates<T> position;
    private Piece<T> piece;

}
