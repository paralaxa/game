package sk.stopangin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.stopangin.field.Action;
import sk.stopangin.field.ActionField;
import sk.stopangin.field.Field;
import sk.stopangin.game.Game;
import sk.stopangin.movement.Coordinates;
import sk.stopangin.movement.Movement;
import sk.stopangin.movement.TwoDimensionalCoordinatesData;
import sk.stopangin.repository.GameRepository;
import sk.stopangin.service.ActionService;

@RestController
@RequestMapping("action")
public class ActionServiceImpl implements ActionService<Integer, TwoDimensionalCoordinatesData, Void> {

    @Autowired
    private GameRepository<TwoDimensionalCoordinatesData, Void> gameRepository;

    public Integer performAction(Long gameId, String data) {
        Game<TwoDimensionalCoordinatesData, Void> game = gameRepository.getById(gameId);
        Action<Integer, TwoDimensionalCoordinatesData, Void> action = getActionForCurrentRound(gameId);
        return action.perform(game, data);
    }

    public Action<Integer, TwoDimensionalCoordinatesData, Void> getActionForCurrentRound(Long gameId) {
        Game<TwoDimensionalCoordinatesData, Void> game = gameRepository.getById(gameId);
        Movement<TwoDimensionalCoordinatesData> movement = game.getActiveRound().getActualPossition();
        Coordinates<TwoDimensionalCoordinatesData> currentRoundPosition = movement.getNewPosition();
        Field<TwoDimensionalCoordinatesData> fieldForCoordinates = game.getBoard().getFieldForCoordinates(currentRoundPosition);
        if (fieldForCoordinates instanceof ActionField) {
            return ((ActionField<Integer, TwoDimensionalCoordinatesData, Void>) fieldForCoordinates).getAction();
        }
        return null;
    }

}
