package sk.stopangin.mapper;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;
import sk.stopangin.game.Round;
import sk.stopangin.game.RoundStatus;
import sk.stopangin.movement.Movement;
import sk.stopangin.movement.TwoDimensionalCoordinatesData;
import sk.stopangin.player.Player;
import sk.stopangin.service.to.game.RoundDto;
import sk.stopangin.service.to.game.RoundStatusDto;
import sk.stopangin.service.to.movement.CoordinatesDto;
import sk.stopangin.service.to.movement.MovementDto;
import sk.stopangin.service.to.movement.TwoDimensionalCoordinatesDataDto;
import sk.stopangin.service.to.player.PlayerDto;

import java.io.Serializable;
import java.util.Optional;

@Component
public class RoundMapper extends CustomMapper<Round<Serializable, Object>, RoundDto<Serializable, Object>> {
    @Override
    public void mapAtoB(Round<Serializable, Object> round, RoundDto<Serializable, Object> roundDto, MappingContext context) {
        roundDto.setActualPossition(createMovementDtoForMovement(round.getActualPossition()));
        roundDto.setData(round.getData());
        roundDto.setPlayer(createPlayerDtoForPlayer(round.getPlayer()));
        roundDto.setRoundStart(round.getRoundStart());
        roundDto.setRoundStatus(createRoundStatusDtoForRoundStatus(round.getRoundStatus()));
    }

    private PlayerDto createPlayerDtoForPlayer(Player<Serializable> player) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(player.getId());
        playerDto.setName(player.getName());
        playerDto.setScore(player.getScore());
        return playerDto;
    }

    private MovementDto<Serializable> createMovementDtoForMovement(Optional<Movement<Serializable>> movement) {
        MovementDto<Serializable> result = null;
        if (movement.isPresent()) {
            result = new MovementDto<>();
            Movement<Serializable> data = movement.get();
            CoordinatesDto<Serializable> coordinatesDto = new CoordinatesDto<>();
            TwoDimensionalCoordinatesDataDto twoDimensionalCoordinatesDataDto = new TwoDimensionalCoordinatesDataDto();
            TwoDimensionalCoordinatesData coordinatesData = (TwoDimensionalCoordinatesData) data.getNewPosition().getData();
            twoDimensionalCoordinatesDataDto.setX(coordinatesData.getX());
            twoDimensionalCoordinatesDataDto.setY(coordinatesData.getY());
            coordinatesDto.setData(twoDimensionalCoordinatesDataDto);
            result.setNewPosition(coordinatesDto);
            result.setPieceId(data.getPieceId());
        }
        return result;
    }

    private RoundStatusDto createRoundStatusDtoForRoundStatus(RoundStatus roundStatus) {
        return new RoundStatusDto(roundStatus.getRoundState().toString(), roundStatus.getErr());
    }
}
