package sk.stopangin.service.to.field;

import lombok.Data;
import sk.stopangin.service.to.movement.CoordinatesDto;
import sk.stopangin.service.to.piece.PieceDto;

import java.io.Serializable;

@Data
public class FieldDto<T extends Serializable> {
    private Long id;
    private CoordinatesDto<T> position;
    private PieceDto piece; //todo promote to set
}
