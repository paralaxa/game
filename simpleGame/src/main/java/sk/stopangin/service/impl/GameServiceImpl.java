package sk.stopangin.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
import sk.stopangin.repository.GameRepository;
import sk.stopangin.repository.QuestionsRepository;
import sk.stopangin.service.GameService;

import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("game")
public class GameServiceImpl implements GameService<TwoDimensionalCoordinatesData, Void> {
    private static final Logger log = LoggerFactory.getLogger(GameServiceImpl.class);

    @Autowired
    private QuestionsRepository questionsRepository;
    @Autowired
    private GameRepository<TwoDimensionalCoordinatesData, Void> gameRepository;
    @RequestMapping(path = "start", method = RequestMethod.GET)
    public Long startGame() {
        List<Question> allQuestions = questionsRepository.getAllQuestions();

        SimpleGame game = new SimpleGame();
        List<Player<TwoDimensionalCoordinatesData>> players = new ArrayList<>();
        Set<Field<TwoDimensionalCoordinatesData>> fields = new SimpleGameFieldsGenerator(9, allQuestions).generateFields();

        Player<TwoDimensionalCoordinatesData> p1 = new SimpleHumanPlayer();
        p1.setName("Player1");

        players.addAll(Arrays.asList(p1));

        SimpleBoard simpleBoard = new SimpleBoard(fields);

        game.startGame(simpleBoard, players);
        gameRepository.save(game);
        return game.getId();
    }

    @RequestMapping(path = "gajaka", method = RequestMethod.POST, consumes ="application/json", produces = "application/json")
    public void gajaka(@RequestBody String gajaka){
        System.out.println(gajaka);
    }

    @RequestMapping(path = "commit", method = RequestMethod.POST, consumes ="application/json", produces = "application/json")
    public Round<TwoDimensionalCoordinatesData, Void> commitRound(@QueryParam("gameId") Long gameId, @RequestBody TwoDimensionalCoordinatesData twoDimensionalCoordinatesData) {
        Game<TwoDimensionalCoordinatesData, Void> game = gameRepository.getById(gameId);
        Movement<TwoDimensionalCoordinatesData> movement = new Movement<>();
        movement.setNewPosition(new TwoDimensionalCoordinates(twoDimensionalCoordinatesData));
        return game.commitRound(movement);
    }

    public static void main(String[] args) {
        GameService gameService = new GameServiceImpl(); //TODO inst oproti Game interfaceu, salt riesit tu.
        Long gameId = gameService.startGame();
        Game<TwoDimensionalCoordinatesData, Void> game = ((GameServiceImpl) gameService).gameRepository.getById(gameId);

        Round<TwoDimensionalCoordinatesData, Void> newRound = gameService.commitRound(gameId, new TwoDimensionalCoordinatesData(1, 2));
        log.debug("new round {}", newRound);
        log.debug("action after 2nd round: {} ", new ActionServiceImpl().getActionForCurrentRound(game.getId()).getActionData());
        new ActionServiceImpl().performAction(game.getId(), "31");

        Round<TwoDimensionalCoordinatesData, Void> newRound2 = gameService.commitRound(gameId, new TwoDimensionalCoordinatesData(1, 3));
        log.debug("new round2 {}", newRound2);

        Round<TwoDimensionalCoordinatesData, Void> newRound3 = gameService.commitRound(gameId, new TwoDimensionalCoordinatesData(2, 3));
        log.debug("new round3 {}", newRound3);

        Round<TwoDimensionalCoordinatesData, Void> newRound4 = gameService.commitRound(gameId, new TwoDimensionalCoordinatesData(2, 4));
        log.debug("new round4 {}", newRound4);


    }


}
