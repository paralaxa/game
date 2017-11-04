package sk.stopangin.service.to.movement;

import lombok.Data;

import java.io.Serializable;

@Data
public class MovementDto<T extends Serializable> {
    private Long pieceId;
    private CoordinatesDto<T> newPosition;
}
