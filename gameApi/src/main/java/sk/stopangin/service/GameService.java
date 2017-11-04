package sk.stopangin.service;

import sk.stopangin.service.to.game.RoundDto;
import sk.stopangin.service.to.board.BoardDto;
import sk.stopangin.service.to.field.ActionDataDto;

import java.io.Serializable;

interface GameService<T extends Serializable, R, A extends Serializable> {

    Long startGame(String playerName);


    RoundDto<T, R> commitRound(Long gameId, T coordinatesData);


    BoardDto<T> getBoard(Long gameId);

    A performAction(Long gameId, String data);


    ActionDataDto getActionDataForCurrentRound(Long gameId);
}
