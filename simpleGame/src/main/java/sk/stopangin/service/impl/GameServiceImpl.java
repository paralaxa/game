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
        Board board = RandomSimpleGameBoardGenerator.generate();
        List<Player> players = new ArrayList<>();

        Set<MovementType> movementTypes = new HashSet<>();
        movementTypes.add(new LinearMovementType());

        Player p1 = new HumanPlayer();//todo zjednodusit, aby vstup na vytvorenie playera bol len playername
        p1.setName("Player1");
//        Piece piece1 = new LinearMovingPiece("p1");
//        piece1.setId(1l);
//        Set<Piece> pieces1 = new HashSet<>();
//        pieces1.add(piece1);
//        p1.setPieces(pieces1);

        Player p2 = new HumanPlayer();
        p2.setName("Player2");
//        Piece piece2 = new LinearMovingPiece("p2");
//        Set<Piece> pieces2 = new HashSet<>();
//        piece2.setId(2l);
//        pieces2.add(piece2);
//        p2.setPieces(pieces2);

        players.addAll(Arrays.asList(p1, p2));

        game.startGame(players);
        return game;
    }


    public Round<Integer> commitRound(Game game) {
        Movement<Integer> movement = getIntegerMovement(0l);
        return game.commitRound(movement);
    }

    public static void main(String[] args) {
        GameServiceImpl gameService = new GameServiceImpl();
        Game game = gameService.startGame(); //ziskat z game repo

        Round<Integer> newRound = gameService.commitRound(game);
        log.debug("new round{}", newRound);

        Round<Integer> newRound2 = gameService.commitRound(game);
        log.debug("new round2{}", newRound2);

    }

    private static Movement<Integer> getIntegerMovement(Long pieceId) {
        Movement<Integer> movement = new Movement<>();
        Piece p = new LinearMovingPiece("");
        p.setId(pieceId);
        movement.setPiece(p);
        return movement;
    }

}
