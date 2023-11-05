package labSolution;

//import java.io.*;
import java.util.*;

import ChatExceptions.PrivateChatExceptions;

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
        prExceptions(ChatApp.PRIVATE_CHAT_LOG_FILE, privateChatLog);
        System.out.println("Private message sent to " + recipient + ".");
    }

        private void prExceptions(String privateChatLogFile, List<String> privateChatLog2) {
    
     PrivateChatExceptions prExceptions = new PrivateChatExceptions();
       prExceptions.saveChatLog(privateChatLogFile, privateChatLog2);
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
}
