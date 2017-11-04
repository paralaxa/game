package sk.stopangin.service.to.movement;

import lombok.Data;

import java.io.Serializable;

@Data
public class TwoDimensionalCoordinatesDataDto implements Serializable {
    private int x;
    private int y;

    public TwoDimensionalCoordinatesDataDto() {
    }

    public TwoDimensionalCoordinatesDataDto(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
