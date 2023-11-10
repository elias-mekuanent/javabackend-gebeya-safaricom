package labSolution;

//import java.io.*;
import java.util.*;

import ChatExceptions.PublicChatExceptions;

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
        PCExceptios(ChatApp.PUBLIC_CHAT_LOG_FILE, publicChatLog);
        System.out.println("Message sent to the public chat.");
    }
    /*
 * using public chat writing exceptions 
 * Kindie Nega
 * back-end
 */
    private void PCExceptios(String privateChatLogFile, List<String> privateChatLog2) {
    
        PublicChatExceptions PCExceptions = new PublicChatExceptions();
          PCExceptions.PublicExceptions(privateChatLogFile, privateChatLog2);
       }

    public void showPublicChatMessages() {
        System.out.println("Public Chat Messages:");
        for (String message : publicChatLog) {
            System.out.println(message);
        }
    }
}
