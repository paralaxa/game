package sk.stopangin.player;

import sk.stopangin.board.Board;
import sk.stopangin.movement.Movement;

public class HumanPlayer extends Player {

    @Override
    public void doMovement(Board board, Movement movement) {
        board.updateBasedOnMovement(movement);
    }
}
