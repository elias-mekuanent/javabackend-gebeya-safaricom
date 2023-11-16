// Package declaration
package labSolution;

// Import statements
import java.util.*;

// Friendslist class definition
public class Friendslist {
    // Instance variable for the friends list
    Map<String, List<String>> friendsList;

    // Constructor to initialize the friends list
    public Friendslist(Map<String, List<String>> friendsList) {
        this.friendsList = friendsList;
    }

    // View the friends list ordered by nicknames
    public void viewFriendsListOrderedByNickname() {
        // Extract nicknames, sort them, and display details
        List<String> nicknames = new ArrayList<>(friendsList.keySet());
        Collections.sort(nicknames);

        System.out.println("Friends List ordered by nicknames:");
        for (String nickname : nicknames) {
            String fullName = "";

            List<String> details = friendsList.get(nickname);
            for (String detail : details) {
                if (detail.startsWith("[FULLNAME]")) {
                    fullName = detail.substring(9);
                    break;
                }
            }
            System.out.println("Nickname: " + nickname);
            System.out.println("Full Name: " + fullName);
        }
    }

    // Print information for a friend by nickname
    public void printInfoByNickname(String nickname) {
        System.out.println("Information for friend with nickname " + nickname + ":");

        List<String> friendDetails = friendsList.get(nickname);

        if (friendDetails != null) {
            for (String detail : friendDetails) {
                System.out.println(detail);
            }
        } else {
            System.out.println("Friend with nickname " + nickname + " not found.");
        }
    }

    // Add a friend to the friends list
    public Map<String, List<String>> addFriend(Scanner scanner) {
        System.out.println("Enter the nickname of the friend you want to add:");
        String recipient = scanner.nextLine();
        System.out.println("Enter the full name of your friend:");
        String fullName = scanner.nextLine();
        System.out.println("Enter the last IP of your friend:");
        String lastIP = scanner.nextLine();

        // Create a new entry for the friend and add it to the friends list
        List<String> friendDetails = new ArrayList<>();
        friendDetails.add("[FULLNAME]" + fullName);
        friendDetails.add("[LASTIP]" + lastIP);
        friendDetails.add("[IMAGE]");

        friendsList.put(recipient, friendDetails);

        System.out.println(recipient + " has been added to your friends list.");
        return friendsList;
    }

    // Getter method for the friends list
    public Map<String, List<String>> getFriendsList() {
        return friendsList;
    }
}
/*commented by Elias Mekuanent
 Java-Backend*/
