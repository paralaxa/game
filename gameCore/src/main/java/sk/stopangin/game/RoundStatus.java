package sk.stopangin.game;

import lombok.Data;

@Data
public class RoundStatus {
    private RoundState roundState;
    private Object err;

    public RoundStatus(RoundState roundState, Object err) {
        this.roundState = roundState;
        this.err = err;
    }

    public RoundStatus(RoundState roundState) {
        this.roundState = roundState;
    }
}
