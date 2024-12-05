package Gcash;
public class User {
    private String name;
    private double balance;
    private String email;
    private String password; // for simplicity in this example

    public User(String name, double balance, String email, String password) {
        this.name = name;
        this.balance = balance;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void showInfo() {
        System.out.println("Name: " + name + ", Balance: " + balance);
    }
}
