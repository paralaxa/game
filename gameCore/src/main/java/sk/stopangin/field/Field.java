package sk.stopangin.field;

import lombok.Data;
import sk.stopangin.piece.Piece;

@Data
public abstract class Field {
    private Integer position;
    private Piece piece;

}
