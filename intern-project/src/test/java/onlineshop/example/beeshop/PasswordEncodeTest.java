package onlineshop.example.beeshop;

import com.google.common.hash.Hashing;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.nio.charset.StandardCharsets;
import java.util.Random;

@SpringBootTest
public class PasswordEncodeTest {

    @Test
    public void encode_password() {
        Random random = new Random();
        String raw = "";
        for (int i = 0; i < 10; ++i) {
            raw += (char) ((int) (random.nextDouble() * (256 - 10) + 10));
        }
        final String hashed = Hashing.sha256().hashString(raw, StandardCharsets.UTF_8).toString();

    }

}
