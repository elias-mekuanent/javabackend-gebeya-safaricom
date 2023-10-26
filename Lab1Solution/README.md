ChatApp
ChatApp is a simple command-line chat application that allows you to send public and private messages, manage your friends list, and view chat logs. It provides a convenient way to interact with your friends and keep track of your conversations.

Features
Public Chat: Send messages that are visible to all users.
Private Chat: Send private messages to your friends.
Add Friends: Add new friends to your friends list with their nickname, full name, last IP, and a shared image.
View Friends List: Display your friends' nicknames and full names ordered by nicknames.
Print Friend Information: View detailed information about a specific friend, including their nickname, full name, last IP, and shared image.
View Public Chat Log: See all public chat messages.
View Private Chat Log: View private messages you've sent to your friends.
Getting Started
Compile the Code: Make sure you have Java installed on your system. Compile the code by running javac ChatApp.java.

Run the Application: Run the application using java ChatApp.

Select Options: The application will display a list of available options. You can send public or private messages, manage your friends list, and view chat logs.

Usage
To send a public message, enter -pb.
To send a private message, enter -pv.
To add a new friend, enter -af. The application will prompt you to enter the nickname, full name, and last IP. The image is shared with all friends.
To view your friends list ordered by nicknames, enter -pf.
To print information about a specific friend, enter -qf nickname, replacing nickname with your friend's nickname.
To view the public chat log, enter -qpl.
To view private chat messages with a specific friend, enter -ql nickname, replacing nickname with your friend's nickname.
Configuration Files
Eurakarte.log: Stores public chat messages.
Donut[AFK].log: Stores private chat messages.
friends.list: Contains your friends' information, including nicknames, full names, last IPs, and shared images.
Note
Make sure to enter the correct nicknames and follow the application's instructions when prompted.
Messages sent to friends are stored in the Donut[AFK].log file, including the friend's nickname.
Author
[Elias Mekuanent]