package sk.stopangin.movement;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Coordinates<T extends Serializable> {
    private T data;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates<?> that = (Coordinates<?>) o;

        return data.equals(that.data);
    }

    @Override
    public int hashCode() {
        return  31 *  data.hashCode();
    }
}
