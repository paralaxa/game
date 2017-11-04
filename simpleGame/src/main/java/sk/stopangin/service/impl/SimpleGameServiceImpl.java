package sk.stopangin.service.impl;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.stopangin.board.SimpleBoard;
import sk.stopangin.field.Action;
import sk.stopangin.field.Field;
import sk.stopangin.field.Question;
import sk.stopangin.field.util.SimpleGameFieldsGenerator;
import sk.stopangin.game.Game;
import sk.stopangin.game.Round;
import sk.stopangin.game.SimpleGame;
import sk.stopangin.mapper.MapperContextInitializer;
import sk.stopangin.movement.Movement;
import sk.stopangin.movement.TwoDimensionalCoordinates;
import sk.stopangin.movement.TwoDimensionalCoordinatesData;
import sk.stopangin.player.Player;
import sk.stopangin.player.SimpleHumanPlayer;
import sk.stopangin.repository.GameRepository;
import sk.stopangin.repository.QuestionsRepository;
import sk.stopangin.service.SimpleGameService;
import sk.stopangin.service.to.board.BoardDto;
import sk.stopangin.service.to.field.ActionDataDto;
import sk.stopangin.service.to.game.RoundDto;
import sk.stopangin.service.to.movement.TwoDimensionalCoordinatesDataDto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("game")
@Api(value = "game", description = "Operations for controlling the game flow.")
public class SimpleGameServiceImpl implements SimpleGameService {
    private static final Logger log = LoggerFactory.getLogger(SimpleGameServiceImpl.class);


    @Autowired
    private QuestionsRepository questionsRepository;
    @Autowired
    private GameRepository<Integer, TwoDimensionalCoordinatesData, Void> gameRepository;
    @Autowired
    private MapperContextInitializer mapper;

    @GetMapping(path = "start")
    public Long startGame(@RequestParam(value = "playerName", required = true) String playerName) {
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

    @SuppressWarnings("unchecked")
    @PostMapping(path = "/{gameId}/commit", consumes = "application/json", produces = "application/json")
    public RoundDto<TwoDimensionalCoordinatesDataDto, Void> commitRound(@PathVariable(value = "gameId", required = true) Long gameId, @RequestBody
            (required = true) TwoDimensionalCoordinatesDataDto twoDimensionalCoordinatesDataDto) {
        log.info("committing round for game:{} with coordinates data:{}", gameId, twoDimensionalCoordinatesDataDto);
        Game<Integer, TwoDimensionalCoordinatesData, Void> game = gameRepository.getById(gameId);
        Movement<TwoDimensionalCoordinatesData> movement = new Movement<>();
        TwoDimensionalCoordinatesData twoDimensionalCoordinatesData = mapper.map(twoDimensionalCoordinatesDataDto, TwoDimensionalCoordinatesData.class);
        movement.setNewPosition(new TwoDimensionalCoordinates(twoDimensionalCoordinatesData));
        Round<TwoDimensionalCoordinatesData, Void> s = game.commitRound(movement);
        return mapper.map(s, RoundDto.class);
    }

    @GetMapping(path = "/{gameId}/board", produces = "application/json")
    public BoardDto<TwoDimensionalCoordinatesDataDto> getBoard(@PathVariable(value = "gameId", required = true) Long gameId) {
        return mapper.map(gameRepository.getById(gameId).getBoard(), BoardDto.class);
    }

    @GetMapping(path = "/{gameId}/action/perform")
    public Integer performAction(@PathVariable(value = "gameId", required = true) Long gameId, @RequestParam("data") String data) {
        Game<Integer, TwoDimensionalCoordinatesData, Void> game = gameRepository.getById(gameId);
        Optional<Action<Integer, TwoDimensionalCoordinatesData, Void>> action = game.getActionForCurrentRound();
        return action.isPresent() ? action.get().perform(game, data) : null;
    }

    @GetMapping(path = "/{gameId}/action")
    public ActionDataDto getActionDataForCurrentRound(@PathVariable(value = "gameId", required = true) Long gameId) {
        Game<Integer, TwoDimensionalCoordinatesData, Void> game = gameRepository.getById(gameId);
        Optional<Action<Integer, TwoDimensionalCoordinatesData, Void>> actionForCurrentRound = game.getActionForCurrentRound();
        if (actionForCurrentRound.isPresent()) {
            return mapper.map(actionForCurrentRound.get().getActionData(), ActionDataDto.class);
        }
        return null;
    }

}
