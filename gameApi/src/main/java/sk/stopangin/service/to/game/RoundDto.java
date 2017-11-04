package sk.stopangin.service.to.game;

import lombok.Data;
import sk.stopangin.service.to.movement.MovementDto;
import sk.stopangin.service.to.player.PlayerDto;

import java.io.Serializable;
import java.time.LocalTime;

@Data
public class RoundDto<T extends Serializable, R> {
    private MovementDto<T> actualPossition;
    private PlayerDto player;
    private  LocalTime roundStart;
    private  R data; //ie dice throw
    private RoundStatusDto roundStatus;
}
