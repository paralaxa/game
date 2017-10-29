package sk.stopangin.movement;

import lombok.Data;

import java.io.Serializable;

@Data
public class TwoDimensionalCoordinatesData implements Serializable {
    private int x;
    private int y;

    public TwoDimensionalCoordinatesData() {
    }

    public TwoDimensionalCoordinatesData(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
