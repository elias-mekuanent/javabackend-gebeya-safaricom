package labSolution;

import java.util.*;
import java.io.*;
import ChatExceptions.SystemExceptionHandler;

public class ChatApp {
    static final String PUBLIC_CHAT_LOG_FILE = "Eurakarte.log";
    static final String PRIVATE_CHAT_LOG_FILE = "Donut[AFK].log";
    static final String FRIENDS_LIST_FILE = "friends.list";

    private List<String> publicChatLog;
    private List<String> privateChatLog;
    private Map<String, List<String>> friendsList;
    
    private String currentUser = "<Donut[AFK]>";

    public ChatApp() {
        publicChatLog = loadChatLog(PUBLIC_CHAT_LOG_FILE);
        privateChatLog = loadChatLog(PRIVATE_CHAT_LOG_FILE);
        friendsList = loadFriendsList(FRIENDS_LIST_FILE); 
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Privatechat privateChat = null;
        Publicchat publicChatQL = null;


        while (true) {
            System.out.println("Select an option:");
            System.out.println("-pb: Send a public message");
            System.out.println("-pv: Send a private message");
            System.out.println("-af: Add a new friend");
            System.out.println("-pf: Print nicknames and real names ordered by nicknames");
            System.out.println("-qf nickname: Print information related to the given nickname");
            System.out.println("-ql nickname: Display the private chat log related to the given nickname");
            System.out.println("-qpl: Display the public chat log");
            System.out.println("Enter 'exit' to exit");

            String choice = scanner.nextLine();

            if (choice.startsWith("-qf ")) {
                String qfNickname = choice.substring(4);
                Friendslist friendListQF = new Friendslist(friendsList);
                friendListQF.printInfoByNickname(qfNickname);

            } else if (choice.startsWith("-ql ")) {
                String qlRecipient = choice.substring(4);
                if (privateChat == null) {
                    privateChat = new Privatechat(privateChatLog, friendsList, currentUser);
                }
                privateChat.showPrivateChatMessagesByRecipient(qlRecipient);
            } else if ("-af".equals(choice)) {
            	Friendslist friendList = new Friendslist(friendsList);
                friendsList = friendList.addFriend(scanner);
                saveFriendsList(FRIENDS_LIST_FILE, friendsList);
            }
                else {
                switch (choice) {
                    case "-pb":
                        if (publicChatQL == null) {
                            publicChatQL = new Publicchat(publicChatLog, currentUser);
                        }
                        publicChatQL.sendPublicMessage(scanner);
                        break;
                    case "-pv":
                        if (privateChat == null) {
                            privateChat = new Privatechat(privateChatLog, friendsList, currentUser);
                        }
                        privateChat.privateChat(scanner);
                        break;
                    case "-pf":
                        Friendslist friendList = new Friendslist(friendsList); // Use Map directly
                        friendList.viewFriendsListOrderedByNickname();
                        break;
                    case "-qpl":
                        if (publicChatQL == null) {
                            publicChatQL = new Publicchat(publicChatLog, currentUser);
                        }
                        publicChatQL.showPublicChatMessages();
                        break;
                    case "exit":
                        System.out.println("Exiting the chat application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            }
        }
    }

    public static void main(String[] args) {
        ChatApp chatApp = new ChatApp();
        chatApp.run();
        Thread.setDefaultUncaughtExceptionHandler(new SystemExceptionHandler());
    }

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