// Package declaration
package labSolution;

// Import statements
import java.util.*;

import ChatExceptions.PublicChatExceptions;

// Publicchat class definition
public class Publicchat {
    // Instance variables for public chat log and current user
    List<String> publicChatLog;
    String currentUser;

    // Constructor to initialize public chat log and current user
    public Publicchat(List<String> publicChatLog, String currentUser) {
        this.publicChatLog = publicChatLog;
        this.currentUser = currentUser;
    }

    // Method to send a public message
    public void sendPublicMessage(Scanner scanner) {
        System.out.println("Enter your message in the public chat:");
        String message = scanner.nextLine();
        publicChatLog.add("you " + "> " + message);
        // Save public chat log with exceptions handling
        PCExceptions(ChatApp.PUBLIC_CHAT_LOG_FILE, publicChatLog);
        System.out.println("Message sent to the public chat.");
    }

    // Method to handle exceptions while saving public chat log
    private void PCExceptions(String publicChatLogFile, List<String> publicChatLog) {
        PublicChatExceptions PCExceptions = new PublicChatExceptions();
        PCExceptions.PublicExceptions(publicChatLogFile, publicChatLog);
    }

    // Method to display public chat messages
    public void showPublicChatMessages() {
        System.out.println("Public Chat Messages:");
        for (String message : publicChatLog) {
            System.out.println(message);
        }
    }
}
/*commented by Elias Mekuanent*/
