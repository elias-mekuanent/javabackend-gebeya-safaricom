package ChatExceptions;

<<<<<<< HEAD
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
/*
 * Private chat writing exceptions 
 * Kindie Nega
 * back-end
 */
public class PrivateChatExceptions {
        public void PrivateExceptions(String fileName, List<String> chatLog) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String message : chatLog) {
                writer.write(message);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
=======
public class PrivateChatException extends Exception {

    public PrivateChatException(String message) {
        super(message);
    }

    public PrivateChatException(String message, Throwable cause) {
        super(message, cause);
>>>>>>> 2d2581c1722733f6864ae574c577d2d41fa03782
    }
}

