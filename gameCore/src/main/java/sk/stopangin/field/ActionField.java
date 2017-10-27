package sk.stopangin.field;

import lombok.Data;

import java.io.Serializable;

@Data
public class ActionField<T extends Serializable, A extends Serializable> extends Field<T> {
    private ActionData<A> actionData;
}
