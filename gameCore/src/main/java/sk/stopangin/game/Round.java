package sk.stopangin.game;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class Round <T extends Serializable>{
    private LocalTime localTime;
    private T data;

}
