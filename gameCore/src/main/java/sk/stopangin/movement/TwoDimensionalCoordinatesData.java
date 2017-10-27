package sk.stopangin.movement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class TwoDimensionalCoordinatesData implements Serializable{
    private String d1;
    private int d2;
}
