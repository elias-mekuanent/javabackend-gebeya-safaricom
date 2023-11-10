package labSolution;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import ChatExceptions.PrivateChatException;

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

    private void savePrivateChatLog(String privateChatLogFile, List<String> privateChatLog) throws PrivateChatException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(privateChatLogFile, true))) {
            for (String message : privateChatLog) {
                writer.println(message);
            }
        } catch (IOException e) {
            throw new PrivateChatException("Error while saving private chat log.", e);
        }
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
