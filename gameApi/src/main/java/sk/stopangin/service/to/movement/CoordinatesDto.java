package sk.stopangin.service.to.movement;

import lombok.Data;

import java.io.Serializable;

@Data
public class CoordinatesDto<T extends Serializable> {
    private T data;
}
