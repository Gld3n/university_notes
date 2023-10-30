package schemas;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class User {

    protected String id;
    protected String name;
    protected String username;
    protected String password;
    protected String phoneNumber;

    // Constructor
    public User(String id, String name, String username, String password, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public String getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    // Setters
    public void setId(String id, Scanner scanner, ArrayList<User> users) {
        this.id = id;
    }
    public void setName(String name, Scanner scanner, ArrayList<User> users) {
        this.name = name;
    }
    public void setUsername(String username, Scanner scanner, ArrayList<User> users) {
        this.username = username;
    }
    public void setPassword(String password, Scanner scanner, ArrayList<User> users) {
        this.password = password;
    }
    public void setPhoneNumber(String phoneNumber, Scanner scanner, ArrayList<User> users) {
        this.phoneNumber = phoneNumber;
    }

    // Methods
    public abstract void menu(Scanner scanner, ArrayList<User> users, User loggedUser);
}
