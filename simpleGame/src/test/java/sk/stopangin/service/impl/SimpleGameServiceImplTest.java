package sk.stopangin.service.impl;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Test;
import sk.stopangin.board.SimpleBoard;
import sk.stopangin.field.Field;
import sk.stopangin.field.util.SimpleGameFieldsGenerator;
import sk.stopangin.game.Round;
import sk.stopangin.game.SimpleGame;
import sk.stopangin.movement.Movement;
import sk.stopangin.movement.TwoDimensionalCoordinates;
import sk.stopangin.movement.TwoDimensionalCoordinatesData;
import sk.stopangin.player.Player;
import sk.stopangin.player.SimpleHumanPlayer;
import sk.stopangin.repository.InMemoryQuestionsRepositoryImpl;
import sk.stopangin.repository.QuestionsRepository;
import sk.stopangin.service.to.game.RoundDto;

import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

public class SimpleGameServiceImplTest {

    @Test
    public void testRound () throws Exception{
        MapperFacade mapperFacade = new DefaultMapperFactory.Builder().build().getMapperFacade();
        SimpleGame game = new SimpleGame();
        QuestionsRepository questionsRepository = new InMemoryQuestionsRepositoryImpl();

        Set<Field<TwoDimensionalCoordinatesData>> fields = new SimpleGameFieldsGenerator(9, questionsRepository.getAllQuestions()).generateFields();
        Player<TwoDimensionalCoordinatesData> p1 = new SimpleHumanPlayer();
        p1.setName("player");
        SimpleBoard simpleBoard = new SimpleBoard(fields);
        Round<TwoDimensionalCoordinatesData, Void> twoDimensionalCoordinatesDataVoidRound = game.startGame(simpleBoard, Arrays.asList(p1));
        assertNotNull(twoDimensionalCoordinatesDataVoidRound);
        TwoDimensionalCoordinates coordinates = new TwoDimensionalCoordinates(new TwoDimensionalCoordinatesData(1, 2));
        Movement<TwoDimensionalCoordinatesData> movement = new Movement<>();
        movement.setNewPosition(coordinates);
        Round<TwoDimensionalCoordinatesData, Void> round2 = game.commitRound(movement);
        RoundDto map = mapperFacade.map(round2, RoundDto.class);
        assertNotNull(map.getActualPossition());
    }
}
