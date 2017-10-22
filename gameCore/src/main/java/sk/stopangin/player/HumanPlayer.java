package sk.stopangin.player;

import sk.stopangin.board.Board;
import sk.stopangin.movement.Movement;
import sk.stopangin.movement.MovementStatus;
import sk.stopangin.piece.Piece;

public class HumanPlayer extends Player {

    @Override
    public MovementStatus doMovement(Board board, Movement movement) {
        return board.updateBasedOnMovement(movement);

    }
}
