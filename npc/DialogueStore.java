package npc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DialogueStore {

    private static final String DIALOGUE_FILE = "dialogues.txt";

    public static String getDialogue(String key) {
        try (InputStream is = DialogueStore.class.getResourceAsStream(DIALOGUE_FILE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2 && parts[0].equals(key)) {
                    return parts[1];
                }
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }

}