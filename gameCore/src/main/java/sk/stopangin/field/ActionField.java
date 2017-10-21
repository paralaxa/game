package sk.stopangin.field;

import lombok.Data;

import java.util.Set;

@Data
public class ActionField extends Field {
    private Set<Action> actions;
}
