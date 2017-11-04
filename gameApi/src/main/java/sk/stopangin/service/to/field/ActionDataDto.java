package sk.stopangin.service.to.field;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ActionDataDto {
    private boolean blocking; //signals, that round cannot be finished without performing action
    private boolean used;//this action was used and won't be fired anymore
    private String info;
    @JsonIgnore
    private String data;
}
