package sk.stopangin.movement;

import lombok.Data;
import sk.stopangin.piece.Piece;

@Data
public class Movement<T> {
    private Piece<T> piece;
    private Coordinates<T> newPocition;

    public Piece<T> getPiece() {
        return piece;
    }

    public void setPiece(final Piece<T> piece) {
        this.piece = piece;
    }

    public Coordinates<T> getNewPocition() {
        return newPocition;
    }

    public void setNewPocition(final Coordinates<T> newPocition) {
        this.newPocition = newPocition;
    }
}
