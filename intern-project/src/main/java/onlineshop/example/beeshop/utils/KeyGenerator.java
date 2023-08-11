package onlineshop.example.beeshop.utils;

import com.google.common.hash.Hashing;
import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.util.Random;
@Getter
public class KeyGenerator {
    private String key;
    public KeyGenerator() {
        Random random = new Random();
        String raw = "";
        for (int i = 0; i < 10; ++i) {
            raw += (char) ((int) (random.nextDouble() * (256 - 10) + 10));
        }
        this.key = Hashing.sha256().hashString(raw, StandardCharsets.UTF_8).toString();

    }

}
