package utils;

import java.util.Random;

public class RandomGeneration {

    // We don't care about having the same seed
    public static final Random RANDOM = new Random();

    public static float randomFloat(float min, float max) {
        return min + RANDOM.nextFloat() * (max - min);
    }

}
