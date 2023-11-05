package ChatExceptions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PrivateChatExceptions {
        public void saveChatLog(String fileName, List<String> chatLog) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String message : chatLog) {
                writer.write(message);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
