// Package declaration and import statements
package labSolution;
import java.util.*;
import java.io.*;
import ChatExceptions.SystemExceptionHandler;

// ChatApp class definition
public class ChatApp {
    // File names for chat logs and friends list
    static final String PUBLIC_CHAT_LOG_FILE = "Eurakarte.log";
    static final String PRIVATE_CHAT_LOG_FILE = "Donut[AFK].log";
    static final String FRIENDS_LIST_FILE = "friends.list";

    // Instance variables for chat logs and friends list
    private List<String> publicChatLog;
    private List<String> privateChatLog;
    private Map<String, List<String>> friendsList;
    
    // Current user's nickname
    private String currentUser = "<Donut[AFK]>";

    // Constructor initializes chat logs and friends list from files
    public ChatApp() {
        publicChatLog = loadChatLog(PUBLIC_CHAT_LOG_FILE);
        privateChatLog = loadChatLog(PRIVATE_CHAT_LOG_FILE);
        friendsList = loadFriendsList(FRIENDS_LIST_FILE); 
    }

    // Main method to run the chat application
    public void run() {
        // Scanner for user input
        Scanner scanner = new Scanner(System.in);
        Privatechat privateChat = null;
        Publicchat publicChatQL = null;

        // Main loop for user interaction
        while (true) {
            // Display menu options
            System.out.println("Select an option:");
            System.out.println("-pb: Send a public message");
            System.out.println("-pv: Send a private message");
            System.out.println("-af: Add a new friend");
            System.out.println("-pf: Print nicknames and real names ordered by nicknames");
            System.out.println("-qf nickname: Print information related to the given nickname");
            System.out.println("-ql nickname: Display the private chat log related to the given nickname");
            System.out.println("-qpl: Display the public chat log");
            System.out.println("Enter 'exit' to exit");

            // Read user input
            String choice = scanner.nextLine();

            // Handle different user choices
            if (choice.startsWith("-qf ")) {
                // View information related to a friend
                Friendslist friendListQF = new Friendslist(friendsList);
                friendListQF.printInfoByNickname(choice.substring(4));
            } else if (choice.startsWith("-ql ")) {
                // Display private chat log related to a friend
                if (privateChat == null) {
                    privateChat = new Privatechat(privateChatLog, friendsList, currentUser);
                }
                privateChat.showPrivateChatMessagesByRecipient(choice.substring(4));
            } else if ("-af".equals(choice)) {
                // Add a new friend to the friends list
                Friendslist friendList = new Friendslist(friendsList);
                friendsList = friendList.addFriend(scanner);
                saveFriendsList(FRIENDS_LIST_FILE, friendsList);
            } else {
                // Handle other menu options
                switch (choice) {
                    case "-pb":
                        // Send a public message
                        if (publicChatQL == null) {
                            publicChatQL = new Publicchat(publicChatLog, currentUser);
                        }
                        publicChatQL.sendPublicMessage(scanner);
                        break;
                    case "-pv":
                        // Send a private message
                        if (privateChat == null) {
                            privateChat = new Privatechat(privateChatLog, friendsList, currentUser);
                        }
                        privateChat.privateChat(scanner);
                        break;
                    case "-pf":
                        // Print friend list ordered by nicknames
                        Friendslist friendList = new Friendslist(friendsList);
                        friendList.viewFriendsListOrderedByNickname();
                        break;
                    case "-qpl":
                        // Display public chat log
                        if (publicChatQL == null) {
                            publicChatQL = new Publicchat(publicChatLog, currentUser);
                        }
                        publicChatQL.showPublicChatMessages();
                        break;
                    case "exit":
                        // Exit the chat application
                        System.out.println("Exiting the chat application.");
                        return;
                    default:
                        // Invalid choice
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            }
        }
    }

    // Main method to start the chat application
    public static void main(String[] args) {
        // Create a ChatApp instance and run the application
        ChatApp chatApp = new ChatApp();
        chatApp.run();
        // Set a custom exception handler
        Thread.setDefaultUncaughtExceptionHandler(new SystemExceptionHandler());
    }

    // Load chat log from a file
    static List<String> loadChatLog(String fileName) {
        List<String> chatLog = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                chatLog.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chatLog;
    }

    // Save chat log to a file
    static void saveChatLog(String fileName, List<String> chatLog) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String message : chatLog) {
                writer.write(message);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load friends list from a file
    static Map<String, List<String>> loadFriendsList(String fileName) {
        Map<String, List<String>> friendsMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            String key = null;
            List<String> details = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("<")) {
                    if (key != null && !details.isEmpty()) {
                        friendsMap.put(key, details);
                        details = new ArrayList<>();
                    }
                    key = line.replace("<", "").replace(">", "");
                } else {
                    details.add(line);
                }
            }
            if (key != null && !details.isEmpty()) {
                friendsMap.put(key, details);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return friendsMap;
    }

    // Save friends list to a file
    static void saveFriendsList(String fileName, Map<String, List<String>> friendsList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, List<String>> entry : friendsList.entrySet()) {
                writer.write("<" + entry.getKey() + ">");
                writer.newLine();
                for (String detail : entry.getValue()) {
                    writer.write(detail);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*Commented by Elias Mekuanent
  Java-Backend*/
