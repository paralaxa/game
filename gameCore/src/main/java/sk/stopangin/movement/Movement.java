package sk.stopangin.movement;

import lombok.Data;
import sk.stopangin.piece.Piece;

import java.io.Serializable;

@Data
public class Movement<T extends Serializable> {
    private Long pieceId;
    private Coordinates<T> newPosition;
}
