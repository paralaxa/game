package sk.stopangin.movement;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class TwoDimensionalCoordinatesData implements Serializable {
    private int x;
    private int y;
}
