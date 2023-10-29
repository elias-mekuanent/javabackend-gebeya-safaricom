package labSolution;

import java.util.*;

public class Friendslist {
	 Map<String, List<String>> friendsList;

	    public Friendslist(Map<String, List<String>> friendsList) {
	        this.friendsList = friendsList;
	    }
   
    public void viewFriendsListOrderedByNickname() {
   
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
                }            }
            System.out.println("Nickname: " + nickname);
            System.out.println("Full Name: " + fullName);
        }
    }

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



    public Map<String, List<String>> addFriend(Scanner scanner) {
        System.out.println("Enter the nickname of the friend you want to add:");
        String recipient = scanner.nextLine();
        System.out.println("Enter the full name of your friend:");
        String fullName = scanner.nextLine();
        System.out.println("Enter the last IP of your friend:");
        String lastIP = scanner.nextLine();

        List<String> friendDetails = new ArrayList<>();
        friendDetails.add("[FULLNAME]" + fullName);
        friendDetails.add("[LASTIP]" + lastIP);
        friendDetails.add("[IMAGE]");

        friendsList.put( recipient , friendDetails);

        System.out.println(recipient + " has been added to your friends list.");
        return friendsList;
    }


    public Map<String, List<String>> getFriendsList() {
        return friendsList;
    }

}
