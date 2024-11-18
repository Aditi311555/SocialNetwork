package socialrec;

import java.util.*;

public class Main {
	    private Map<String, Set<String>> graph; // Store user relationships
	    private Set<String> users; // Store registered users
	    private String loggedInUser; // Track the logged-in user

	    public Main() {
	        graph = new HashMap<>();
	        users = new HashSet<>();
	        loggedInUser = null;
	        initializeUsers();
	    }

	    // Initialize with preexisting users and friendships
	    private void initializeUsers() {
	        String[] initialUsers = {"Alice Johnson", "Bob Smith", "Charlie Brown", "David Williams", "Eva Miller"};
	        for (String user : initialUsers) {
	            signUp(user);
	        }
	        addFriendship("Alice Johnson", "Bob Smith");
	        addFriendship("Alice Johnson", "Charlie Brown");
	        addFriendship("Bob Smith", "David Williams");
	        addFriendship("Charlie Brown", "Eva Miller");
	        addFriendship("David Williams", "Eva Miller");
	    }

	    // Sign up a new user
	    public void signUp(String username) {
	        if (!users.contains(username)) {
	            users.add(username);
	            graph.put(username, new HashSet<>());
	            System.out.println(username + " signed up successfully.");
	        } else {
	            System.out.println("Username " + username + " is already taken.");
	        }
	    }

	    // Log in an existing user
	    public boolean login(String username) {
	        if (users.contains(username)) {
	            loggedInUser = username;
	            System.out.println(username + " logged in successfully.");
	            return true;
	        } else {
	            System.out.println("Username " + username + " does not exist. Please sign up.");
	            return false;
	        }
	    }

	    // Log out the current user
	    public void logout() {
	        if (loggedInUser != null) {
	            System.out.println(loggedInUser + " logged out.");
	            loggedInUser = null;
	        } else {
	            System.out.println("No user is currently logged in.");
	        }
	    }

	    // Add a friendship between two users
	    private void addFriendship(String u1, String u2) {
	        graph.get(u1).add(u2);
	        graph.get(u2).add(u1);
	    }

	    // Display friends of the logged-in user
	    public void viewFriends() {
	        Set<String> friends = graph.get(loggedInUser);
	        if (friends.isEmpty()) {
	            System.out.println("You have no friends yet.");
	        } else {
	            System.out.println("Your friends: " + friends);
	        }
	    }

	    // Display top users on the platform
	    public void viewTopUsers() {
	        List<String> topUsers = new ArrayList<>(graph.keySet());
	        topUsers.sort((a, b) -> Integer.compare(graph.get(b).size(), graph.get(a).size()));

	        System.out.println("Top users on the platform: " + topUsers);
	    }

	    // Display friend recommendations for the logged-in user
	    public void viewRecommendations() {
	        List<String> recommendations = getRecommendations(loggedInUser);
	        if (recommendations.isEmpty()) {
	            System.out.println("No recommendations available. Make some friends first!");
	        } else {
	            System.out.println("Friend recommendations for you: " + recommendations);
	        }
	    }

	    // Private helper to get friend recommendations for a user
	    private List<String> getRecommendations(String user) {
	        Map<String, Integer> suggestions = new HashMap<>();
	        Set<String> directFriends = graph.get(user);

	        for (String friend : directFriends) {
	            for (String fof : graph.get(friend)) {
	                if (!directFriends.contains(fof) && !fof.equals(user)) {
	                    suggestions.put(fof, suggestions.getOrDefault(fof, 0) + 1);
	                }
	            }
	        }

	        List<String> recommendationList = new ArrayList<>(suggestions.keySet());
	        recommendationList.sort((a, b) -> Integer.compare(suggestions.get(b), suggestions.get(a)));

	        return recommendationList;
	    }

	    public static void main(String[] args) {
	        Main network = new Main();
	        Scanner scanner = new Scanner(System.in);
	        boolean running = true;

	        while (running) {
	            System.out.println("\n--- Social Network Main Menu ---");
	            System.out.println("1. Sign Up");
	            System.out.println("2. Log In");
	            System.out.println("3. Exit");
	            System.out.print("Choose an option: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine(); // Consume newline

	            switch (choice) {
	                case 1:
	                    System.out.print("Enter username to sign up: ");
	                    String newUsername = scanner.nextLine();
	                    network.signUp(newUsername);
	                    break;

	                case 2:
	                    System.out.print("Enter username to log in: ");
	                    String username = scanner.nextLine();
	                    if (network.login(username)) {
	                        boolean loggedIn = true;
	                        while (loggedIn) {
	                            System.out.println("\n--- Logged In Menu ---");
	                            System.out.println("1. View Friends");
	                            System.out.println("2. View Top Users");
	                            System.out.println("3. View Friend Recommendations");
	                            System.out.println("4. Add Friend");
	                            System.out.println("5. Log Out");
	                            System.out.print("Choose an option: ");
	                            int loggedInChoice = scanner.nextInt();
	                            scanner.nextLine(); // Consume newline

	                            switch (loggedInChoice) {
	                                case 1:
	                                    network.viewFriends();
	                                    break;
	                                case 2:
	                                    network.viewTopUsers();
	                                    break;
	                                case 3:
	                                    network.viewRecommendations();
	                                    break;
	                                case 4:
	                                    System.out.print("Enter the name of the user to add as a friend: ");
	                                    String friendUsername = scanner.nextLine();
	                                    network.addFriendship(username, friendUsername);
	                                    System.out.println("Friendship added!");
	                                    break;
	                                case 5:
	                                    network.logout();
	                                    loggedIn = false;
	                                    break;
	                                default:
	                                    System.out.println("Invalid option. Please try again.");
	                            }
	                        }
	                    }
	                    break;

	                case 3:
	                    System.out.println("Exiting the program...");
	                    running = false;
	                    break;

	                default:
	                    System.out.println("Invalid option. Please try again.");
	            }
	        }
	        scanner.close();
	    }
	}
