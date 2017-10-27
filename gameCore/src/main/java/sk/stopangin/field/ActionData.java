package sk.stopangin.field;

import lombok.Data;
import sk.stopangin.entity.BaseIdentifiableEntity;

import java.io.Serializable;

@Data
public class ActionData<A extends Serializable> extends BaseIdentifiableEntity {
    private boolean blocking; //signals, that round cannot be finished without performing action
    private String info;
    private String data;
    private Action<A> action;
}
