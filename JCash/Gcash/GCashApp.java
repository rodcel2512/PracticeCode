package Gcash;
import java.util.*;
public class GCashApp {
    private List<User> users;

    public GCashApp() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean authenticateUser(String email, String password) {
        return users.stream().anyMatch(user -> user.getEmail().equals(email) && user.getPassword().equals(password));
    }

    public double checkBalance(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst().map(User::getBalance).orElse(0.0);
    }

    public void cashIn(String email, double amount) {
        users.stream().filter(user -> user.getEmail().equals(email)).findFirst().ifPresent(user -> user.setBalance(user.getBalance() + amount));
    }

    public void cashTransfer(String fromEmail, String toEmail, double amount) {
        User fromUser = users.stream().filter(user -> user.getEmail().equals(fromEmail)).findFirst().orElse(null);
        User toUser = users.stream().filter(user -> user.getEmail().equals(toEmail)).findFirst().orElse(null);

        if (fromUser != null && toUser != null && fromUser.getBalance() >= amount) {
            fromUser.setBalance(fromUser.getBalance() - amount);
            toUser.setBalance(toUser.getBalance() + amount);
            System.out.println("Transaction successful: " + fromUser.getName() + " sent " + amount + " to " + toUser.getName());
        } else {
            System.out.println("Transaction failed: Insufficient balance or user not found");
        }
    }

    public void showTransactions() {
        // Placeholder for transaction display logic
        System.out.println("Displaying transactions...");
    }

    public static void main(String[] args) {
        GCashApp app = new GCashApp();

        // Adding users
        User customer1 = new User("Alice", 5000, "alice@example.com", "password123");
        User merchant1 = new User("Bob's Store", 10000, "bobstore@example.com", "storepassword");

        app.addUser(customer1);
        app.addUser(merchant1);

        // Authentication test
        System.out.println("Authentication (valid): " + app.authenticateUser("alice@example.com", "password123")); // true
        System.out.println("Authentication (invalid): " + app.authenticateUser("alice@example.com", "wrongpassword")); // false

        // Check balance
        System.out.println("Balance for Alice: " + app.checkBalance("alice@example.com")); // 5000

        // Cash in
        app.cashIn("alice@example.com", 2000);
        System.out.println("Balance for Alice after cash in: " + app.checkBalance("alice@example.com")); // 7000

        // Cash transfer
        app.cashTransfer("alice@example.com", "bobstore@example.com", 1500);
        System.out.println("Balance for Alice after transfer: " + app.checkBalance("alice@example.com")); // 5500
        System.out.println("Balance for Bob's Store after transfer: " + app.checkBalance("bobstore@example.com")); // 11500

        // Display transactions
        app.showTransactions();
    }
}
