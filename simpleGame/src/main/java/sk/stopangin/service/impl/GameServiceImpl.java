package sk.stopangin.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.stopangin.board.Board;
import sk.stopangin.board.RandomSimpleGameBoardGenerator;
import sk.stopangin.game.Game;
import sk.stopangin.game.Round;
import sk.stopangin.game.SimpleGame;
import sk.stopangin.movement.LinearMovementType;
import sk.stopangin.movement.Movement;
import sk.stopangin.movement.MovementType;
import sk.stopangin.piece.LinearMovingPiece;
import sk.stopangin.piece.Piece;
import sk.stopangin.player.HumanPlayer;
import sk.stopangin.player.Player;
import sk.stopangin.player.SimpleHumanPlayer;
import sk.stopangin.repository.GameRepository;
import sk.stopangin.service.GameService;

import java.util.*;

@Component
public class GameServiceImpl implements GameService {
    private static final Logger log = LoggerFactory.getLogger(GameServiceImpl.class);

    @Autowired
    private GameRepository gameRepository;

    public Game startGame() {
        SimpleGame game = new SimpleGame();
        List<Player<Integer>> players = new ArrayList<>();

        Set<MovementType> movementTypes = new HashSet<>();
        movementTypes.add(new LinearMovementType());

        Player<Integer> p1 = new SimpleHumanPlayer();
        p1.setName("Player1");

        Player<Integer> p2 = new SimpleHumanPlayer();
        p2.setName("Player2");

        players.addAll(Arrays.asList(p1, p2));

        game.startGame(players);
        return game;
    }


    public Round<Integer> commitRound(Game game) {
        return game.commitRound(new Movement<>());
    }

    public static void main(String[] args) {
        GameServiceImpl gameService = new GameServiceImpl(); //TODO inst oproti Game interfaceu, salt riesit tu.
        Game game = gameService.startGame(); //ziskat z game repo

        Round<Integer> newRound = gameService.commitRound(game);
        log.debug("new round {}", newRound);

        Round<Integer> newRound2 = gameService.commitRound(game);
        log.debug("new round2 {}", newRound2);

        Round<Integer> newRound3 = gameService.commitRound(game);
        log.debug("new round3 {}", newRound3);

        Round<Integer> newRound4 = gameService.commitRound(game);
        log.debug("new round4 {}", newRound4);

    }


}
