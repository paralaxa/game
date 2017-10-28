package sk.stopangin.field;

import lombok.Data;

import java.io.Serializable;

@Data
public class ActionField<A extends Serializable, T extends Serializable,R> extends Field<T> {
    private Action<A,T,R> action;
}
