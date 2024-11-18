Social Network Simulation
This project is a basic simulation of a social network using Java's HashMap and HashSet for efficient user management and relationship tracking. It features user sign-up, login, friendship management, and recommendation functionalities.

Features
User Sign-Up and Login: Allows new users to register and existing users to log in.
Friendship Management: Users can add friends and view their friend list.
Top Users View: Displays users ranked by their number of friends.
Friend Recommendations: Suggests friends based on mutual connections.
How It Works
Data Structures:
Map<String, Set<String>> is used to store users and their friendships.
Set<String> is used to maintain a list of registered users.
Initialization: The application starts with a set of predefined users and friendships.
Recommendation Algorithm: Uses mutual friends to suggest potential new connections.
How to Run
Clone the repository.
Compile the Java file:
css
Copy code
javac Main.java
Run the program:
css
Copy code
java Main
Follow the menu options to interact with the network simulation.
Example
mathematica
Copy code
--- Social Network Main Menu ---
1. Sign Up
2. Log In
3. Exit
Choose an option: 
Sign up with a username.
Log in to view friends, top users, or get friend recommendations.
Add new friends to expand your social network.
