package labSolution;

import java.io.*;
import java.util.*;

public class Privatechat {
    List<String> privateChatLog;
    Map<String, List<String>> friendsList;
    String currentUser;

    public Privatechat(List<String> privateChatLog, Map<String, List<String>> friendsList, String currentUser) {
        this.privateChatLog = privateChatLog;
        this.friendsList = friendsList;
        this.currentUser = currentUser;
    }

    public void privateChat(Scanner scanner) {
        System.out.println("Enter the recipient's nickname:");
        String recipient = scanner.nextLine();
        
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

        System.out.println("Enter your private message:");
        String message = scanner.nextLine();
        String senderMessage = "<< you >> to <" + recipient + "> " + message; 
        privateChatLog.add(senderMessage);
        saveChatLog(ChatApp.PRIVATE_CHAT_LOG_FILE, privateChatLog);
        System.out.println("Private message sent to " + recipient + ".");
    }

    public void showPrivateChatMessagesByRecipient(String recipient) {
        System.out.println("Private Chat Messages for recipient " + recipient + ":");
        boolean messagesFound = false;
        for (String message : privateChatLog) {
            if (message.contains("<< you >> to <" + recipient + ">")) { 
                System.out.println(message);
                messagesFound = true;
            }
        }
        if (!messagesFound) {
            System.out.println("No messages yet");
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
