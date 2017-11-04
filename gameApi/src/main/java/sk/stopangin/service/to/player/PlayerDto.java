package sk.stopangin.service.to.player;

import lombok.Data;

import java.io.Serializable;
@Data
public class PlayerDto implements Serializable {
    private Long id;
    private String name;
    private int score;
}
