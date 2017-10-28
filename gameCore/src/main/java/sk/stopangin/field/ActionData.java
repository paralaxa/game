package sk.stopangin.field;

import lombok.Data;
import sk.stopangin.entity.BaseIdentifiableEntity;

@Data
public class ActionData extends BaseIdentifiableEntity {
    private boolean blocking; //signals, that round cannot be finished without performing action
    private boolean used;//this action was used and won't be fired anymore
    private String info;
    private String data;
}
