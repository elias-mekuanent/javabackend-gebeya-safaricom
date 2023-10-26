package labSolution;

import java.io.*;
import java.util.*;

public class Publicchat {
    List<String> publicChatLog;
    String currentUser;

    public Publicchat(List<String> publicChatLog, String currentUser) {
        this.publicChatLog = publicChatLog;
        this.currentUser = currentUser;
    }

    public void sendPublicMessage(Scanner scanner) {
        System.out.println("Enter your message in the public chat:");
        String message = scanner.nextLine();
        publicChatLog.add("you "+ "> " + message);
        saveChatLog(ChatApp.PUBLIC_CHAT_LOG_FILE, publicChatLog);
        System.out.println("Message sent to the public chat.");
    }

    public void showPublicChatMessages() {
        System.out.println("Public Chat Messages:");
        for (String message : publicChatLog) {
            System.out.println(message);
        }
    }

    private void saveChatLog(String fileName, List<String> chatLog) {
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
