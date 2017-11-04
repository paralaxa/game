package sk.stopangin.service.to.board;

import lombok.Data;
import sk.stopangin.service.to.field.FieldDto;

import java.io.Serializable;
import java.util.Set;

@Data
public class BoardDto <T extends Serializable>{
    private Set<FieldDto<T>> fields;
}
