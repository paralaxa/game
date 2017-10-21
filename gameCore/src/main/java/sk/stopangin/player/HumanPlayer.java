package sk.stopangin.player;

import sk.stopangin.board.Board;
import sk.stopangin.movement.Movement;
import sk.stopangin.piece.Piece;

public class HumanPlayer extends Player {

    @Override
    public void doMovement(Board board, Movement movement) {
        Piece piece = movement.getPiece();
        if (piece.isValidMove(movement.getNewPocition())) {
            board.updateBasedOnMovement(movement);
        }
    }
}
