package sk.stopangin.board;

import sk.stopangin.movement.Movement;

public interface Board {
   boolean isMoveWithinBoundaries(Movement movement);
}
