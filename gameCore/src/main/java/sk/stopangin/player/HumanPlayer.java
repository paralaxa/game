package sk.stopangin.player;

import sk.stopangin.board.Board;
import sk.stopangin.movement.Movement;
import sk.stopangin.movement.MovementStatus;

import java.io.Serializable;

public class HumanPlayer<T extends Serializable> extends Player<T> {

    @Override
    public MovementStatus doMovement(Board<T> board, Movement<T> movement) {
        return board.updateBasedOnMovement(movement);

    }
}
