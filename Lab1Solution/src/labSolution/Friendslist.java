package labSolution;

import java.util.*;

public class Friendslist {
    List<String> friendsList;

    public Friendslist(List<String> friendsList) {
        this.friendsList = friendsList;
    }

    public void viewFriendsListOrderedByNickname() {
        List<String> nicknames = new ArrayList<>();
        Map<String, String> friendInfo = new HashMap<>();

        for (String friend : friendsList) {
            if (friend.startsWith("<") && friend.endsWith(">")) {
                String nickname = friend.substring(1, friend.length() - 1);
                nicknames.add(nickname);
                friendInfo.put(nickname, "");
            } else if (friend.startsWith("[FULLNAME]")) {
                String fullName = friend.substring(9);
                friendInfo.put(nicknames.get(nicknames.size() - 1), fullName);
            }
        }

        Collections.sort(nicknames);
        System.out.println("Friends List ordered by nicknames:");
        for (String nickname : nicknames) {
            System.out.println("Nickname: " + nickname);
            System.out.println("Full Name: " + friendInfo.get(nickname));
        }
    }

    public void printInfoByNickname(String nickname) {
        boolean found = false;
        System.out.println("Information for friend with nickname " + nickname + ":");

        for (int i = 0; i < friendsList.size(); i++) {
            String friend = friendsList.get(i);
            if (friend.startsWith("<" + nickname + ">")) {
                found = true;
                System.out.println(friend);
                for (int j = i + 1; j < i + 4; j++) {
                    if (j < friendsList.size()) {
                        System.out.println(friendsList.get(j));
                    }
                }
                break;
            }
        }

        if (!found) {
            System.out.println("Friend with nickname " + nickname + " not found.");
        }
    }

    public List<String> addFriend(Scanner scanner) {
        System.out.println("Enter the nickname of the friend you want to add:");
        String recipient = scanner.nextLine();
        System.out.println("Enter the full name of your friend:");
        String fullName = scanner.nextLine();
        System.out.println("Enter the last IP of your friend:");
        String lastIP = scanner.nextLine();

        friendsList.add("<" + recipient + ">");
        friendsList.add("[FULLNAME]" + fullName);
        friendsList.add("[LASTIP]" + lastIP);
        friendsList.add("[IMAGE]");

        System.out.println(recipient + " has been added to your friends list.");
        return friendsList;
    }

    public List<String> getFriendsList() {
        return friendsList;
    }
}
