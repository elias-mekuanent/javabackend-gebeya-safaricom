// Package declaration
package labSolution;

// Import statements
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import ChatExceptions.PrivateChatException;

// Privatechat class definition
public class Privatechat {
    // Instance variables for private chat log, friends list, and current user
    List<String> privateChatLog;
    Map<String, List<String>> friendsList;
    String currentUser;

    // Constructor to initialize private chat log, friends list, and current user
    public Privatechat(List<String> privateChatLog, Map<String, List<String>> friendsList, String currentUser) {
        this.privateChatLog = privateChatLog;
        this.friendsList = friendsList;
        this.currentUser = currentUser;
    }

    // Method to handle private chat interactions
    public void privateChat(Scanner scanner) {
        System.out.println("Enter the recipient's nickname:");
        String recipient = scanner.nextLine();

        // Check if recipient is in the friends list
        if (!friendsList.containsKey(recipient)) {
            System.out.println("Recipient not found in your friends list.");
            System.out.println("Do you want to add " + recipient + " as a friend? (yes/no)");
            String choice = scanner.nextLine();
            if ("yes".equalsIgnoreCase(choice)) {
                Friendslist friendList = new Friendslist(friendsList);
                friendsList = friendList.addFriend(scanner);
                ChatApp.saveFriendsList(ChatApp.FRIENDS_LIST_FILE, friendsList);
            }
            return;
        }

        try {
            System.out.println("Enter your private message:");
            String message = scanner.nextLine();
            String senderMessage = "<< you >> to <" + recipient + "> " + message;
            privateChatLog.add(senderMessage);
            savePrivateChatLog(ChatApp.PRIVATE_CHAT_LOG_FILE, privateChatLog);
            System.out.println("Private message sent to " + recipient + ".");
        } catch (PrivateChatException e) {
            System.out.println("Error while saving private chat log: " + e.getMessage());
        }
    }

    // Method to save private chat log to a file
    private void savePrivateChatLog(String privateChatLogFile, List<String> privateChatLog) throws PrivateChatException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(privateChatLogFile, true))) {
            for (String message : privateChatLog) {
                writer.println(message);
            }
        } catch (IOException e) {
            throw new PrivateChatException("Error while saving private chat log.", e);
        }
    }

    // Method to display private chat messages for a specific recipient
    public void showPrivateChatMessagesByRecipient(String recipient) {
        System.out.println("Private Chat Messages for recipient " + recipient + ":");
        boolean messagesFound = false;
        for (String message : privateChatLog) {
            if (message.contains("<< you >> to <" + recipient + ">")) {
                System.out.println(message);
                messagesFound = true;
            }
        }
        /*commented by Elias Mekuanent*/
        if (!messagesFound) {
            System.out.println("No messages yet");
        }
    }
}
