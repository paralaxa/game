package sk.stopangin.field;

import lombok.Data;
import sk.stopangin.game.Game;
import sk.stopangin.movement.Coordinates;
import sk.stopangin.movement.Movement;
import sk.stopangin.movement.TwoDimensionalCoordinatesData;
import sk.stopangin.player.Player;

import java.util.Optional;

@Data
public class QuestionAction extends Action<Integer, TwoDimensionalCoordinatesData, Void> {
    private int score;

    @Override
    public Integer perform(Game<Integer, TwoDimensionalCoordinatesData, Void> game) {
        throw new UnsupportedOperationException("Use perform with game and data param.");
    }

    @Override
    public Integer perform(Game<Integer, TwoDimensionalCoordinatesData, Void> game, String data) {
        int roundScore;
        if (getActionData().isUsed()) {
            return 0;
        }
        if (getActionData().getData().equals(data)) {
            roundScore = score;
        } else {
            roundScore = -score;
        }
        Optional<Movement<TwoDimensionalCoordinatesData>> actualPossition = game.getActiveRound().getActualPossition();
        if (actualPossition.isPresent()) {
            Coordinates<TwoDimensionalCoordinatesData> newPosition = actualPossition.get().getNewPosition();
            Field<TwoDimensionalCoordinatesData> fieldForCoordinates = game.getBoard().getFieldForCoordinates(newPosition);
            ((ActionField) fieldForCoordinates).getAction().getActionData().setUsed(true);
            Player<TwoDimensionalCoordinatesData> activePlayer = game.getActiveRound().getPlayer();
            activePlayer.setScore(activePlayer.getScore() + roundScore);
            game.commitRound(actualPossition.get());
        }
        return roundScore;
    }
}
