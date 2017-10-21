package sk.stopangin.game;

import java.io.Serializable;

public interface RoundDataGenerator<T extends Serializable> {
    T generate();
}
