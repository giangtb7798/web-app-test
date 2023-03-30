package utils;

import static org.apache.commons.lang3.RandomStringUtils.random;

public class RandomStringUtils {
    public static String randomAlphabetic(int count) {
        return random(count, true, false);
    }
}
