package xyz.mynt.wcbootcamp.utility;

import org.springframework.util.StringUtils;

import java.util.UUID;

public class RandomGeneratorUtils {

    public static String generateRandomUUID() {
        return StringUtils.replace(UUID.randomUUID().toString(), "-", "");
    }

    private RandomGeneratorUtils() {}
}