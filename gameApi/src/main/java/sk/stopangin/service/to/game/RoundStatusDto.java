package sk.stopangin.service.to.game;

import lombok.Data;

@Data
public class RoundStatusDto {
    private String roundState;
    private Object err;

    public RoundStatusDto(String roundState, Object err) {
        this.roundState = roundState;
        this.err = err;
    }

    public RoundStatusDto(String roundState) {
        this.roundState = roundState;
    }
}
