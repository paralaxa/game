package sk.stopangin.field;

import lombok.Data;
import sk.stopangin.game.Game;
import sk.stopangin.movement.TwoDimensionalCoordinatesData;
import sk.stopangin.player.Player;
@Data
public class QuestionAction extends Action<Integer, TwoDimensionalCoordinatesData, Void> {
    private int score;

    @Override
    public Integer perform(Game<TwoDimensionalCoordinatesData, Void> game) {
        return null;
    }

    @Override
    public Integer perform(Game<TwoDimensionalCoordinatesData, Void> game, String data) {
        int roundScore;
        if (getActionData().getData().equals(data)) {
            roundScore = score;
        } else {
            roundScore = -score;
        }
        Player<TwoDimensionalCoordinatesData> activePlayer = game.getActiveRound().getPlayer();
        activePlayer.setScore(activePlayer.getScore() + roundScore);
        return roundScore;
    }
}
