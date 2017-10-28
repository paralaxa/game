package sk.stopangin.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import sk.stopangin.board.SimpleBoard;
import sk.stopangin.field.Field;
import sk.stopangin.field.Question;
import sk.stopangin.field.util.SimpleGameFieldsGenerator;
import sk.stopangin.game.Game;
import sk.stopangin.game.Round;
import sk.stopangin.game.SimpleGame;
import sk.stopangin.movement.Movement;
import sk.stopangin.movement.TwoDimensionalCoordinates;
import sk.stopangin.movement.TwoDimensionalCoordinatesData;
import sk.stopangin.player.Player;
import sk.stopangin.player.SimpleHumanPlayer;
import sk.stopangin.repository.InMemoryQuestionsRepositoryImpl;
import sk.stopangin.repository.QuestionsRepository;
import sk.stopangin.service.GameService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class GameServiceImpl implements GameService {
    private static final Logger log = LoggerFactory.getLogger(GameServiceImpl.class);


    private QuestionsRepository questionsRepository = new InMemoryQuestionsRepositoryImpl();

    public Game startGame() {
        List<Question> allQuestions = questionsRepository.getAllQuestions();

        SimpleGame game = new SimpleGame();
        List<Player<TwoDimensionalCoordinatesData>> players = new ArrayList<>();
        Set<Field<TwoDimensionalCoordinatesData>> fields = new SimpleGameFieldsGenerator(9, allQuestions).generateFields();

        Player<TwoDimensionalCoordinatesData> p1 = new SimpleHumanPlayer();
        p1.setName("Player1");

        Player<TwoDimensionalCoordinatesData> p2 = new SimpleHumanPlayer();
        p2.setName("Player2");

        Player<TwoDimensionalCoordinatesData> p3 = new SimpleHumanPlayer();
        p3.setName("Player3");

        players.addAll(Arrays.asList(p1));

        SimpleBoard simpleBoard = new SimpleBoard(fields);


        game.startGame(simpleBoard,players);
        return game;
    }

    public Round<TwoDimensionalCoordinatesData, Void> commitRound(Game game) {
        Movement<TwoDimensionalCoordinatesData> movement = new Movement<>();
        movement.setNewPosition(new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(1,2)));
        return game.commitRound(movement);
    }

    public static void main(String[] args) {
        GameServiceImpl gameService = new GameServiceImpl(); //TODO inst oproti Game interfaceu, salt riesit tu.
        Game game = gameService.startGame(); //ziskat z game repo

        Round<TwoDimensionalCoordinatesData, Void> newRound = gameService.commitRound(game);
        log.debug("new round {}", newRound);

        Round<TwoDimensionalCoordinatesData, Void> newRound2 = gameService.commitRound(game);
        log.debug("new round2 {}", newRound2);

        Round<TwoDimensionalCoordinatesData, Void> newRound3 = gameService.commitRound(game);
        log.debug("new round3 {}", newRound3);

        Round<TwoDimensionalCoordinatesData, Void> newRound4 = gameService.commitRound(game);
        log.debug("new round4 {}", newRound4);

    }


}
