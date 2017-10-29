package sk.stopangin.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.stopangin.board.Board;
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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.QueryParam;
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

    @GetMapping(path = "start")
    public Long startGame(@RequestParam(value = "playerName", required = true)  String playerName) {
        List<Question> allQuestions = questionsRepository.getAllQuestions();
        SimpleGame game = new SimpleGame();
        Set<Field<TwoDimensionalCoordinatesData>> fields = new SimpleGameFieldsGenerator(9, allQuestions).generateFields();
        Player<TwoDimensionalCoordinatesData> p1 = new SimpleHumanPlayer();
        p1.setName(playerName);
        SimpleBoard simpleBoard = new SimpleBoard(fields);
        game.startGame(simpleBoard, Arrays.asList(p1));
        gameRepository.save(game);
        log.info("starting game:{} with player:{}", game.getId(), playerName);
        return game.getId();
    }

    @PostMapping(path = "/{gameId}/commit", consumes = "application/json", produces = "application/json")
    public Round<TwoDimensionalCoordinatesData, Void> commitRound(@PathVariable(value = "gameId", required = true)  Long gameId, @RequestBody(required = true)TwoDimensionalCoordinatesData twoDimensionalCoordinatesData) {
        log.info("committing round for game:{} with coordinates data:{}", gameId, twoDimensionalCoordinatesData);
        Game<TwoDimensionalCoordinatesData, Void> game = gameRepository.getById(gameId);
        Movement<TwoDimensionalCoordinatesData> movement = new Movement<>();
        movement.setNewPosition(new TwoDimensionalCoordinates(twoDimensionalCoordinatesData));
        return game.commitRound(movement);
    }


    @GetMapping(path = "/{gameId}/board", produces = "application/json")
    public Board<TwoDimensionalCoordinatesData> getBoard(@PathVariable("gameId") @NotNull Long gameId) {
        return gameRepository.getById(gameId).getBoard();
    }
}
