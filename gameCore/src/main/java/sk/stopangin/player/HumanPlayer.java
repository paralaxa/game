package sk.stopangin.player;

import sk.stopangin.board.Board;
import sk.stopangin.movement.Movement;
import sk.stopangin.movement.MovementStatus;

public class HumanPlayer<T> extends Player<T> {

    @Override
    public MovementStatus doMovement(Board<T> board, Movement<T> movement) {
        return board.updateBasedOnMovement(movement);

    }
}
