package sk.stopangin.game;

import java.util.Random;

public class CubeThrowRoundDataGenerator implements RoundDataGenerator<Integer> {

    public CubeThrowRoundDataGenerator(int maxValue) {
        this.maxValue = maxValue;
    }

    private int maxValue;

    @Override
    public Integer generate() {
        Random random = new Random();
        return random.nextInt(maxValue) + 1;
    }
}
