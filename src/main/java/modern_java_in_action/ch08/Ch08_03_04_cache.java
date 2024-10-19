package modern_java_in_action.ch08;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ch08_03_04_cache {
    private MessageDigest messageDigest;


    public static void main(String[] args) {
        new Ch08_03_04_cache().main();
    }

    public Ch08_03_04_cache() {
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private byte[] calculateDigest(String key) {
        return messageDigest.digest(key.getBytes(StandardCharsets.UTF_8));
    }

    private void main() {
        List<String> lines = Arrays.asList(
                " Nel   mezzo del cammin  di nostra  vita ",
                "mi  ritrovai in una  selva oscura",
                " che la  dritta via era   smarrita "
        );

        Map<String, byte[]> dataToHash = new HashMap<>();
        lines.forEach(line -> dataToHash.computeIfAbsent(line, this::calculateDigest));

        System.out.println(dataToHash);
    }
}
