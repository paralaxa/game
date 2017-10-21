package sk.stopangin.game;

import lombok.Data;
import sk.stopangin.movement.Movement;

@Data
public abstract class Rule {
    private String ruleCode;

    public abstract boolean isValidMovement(Movement movement);
}
