package schemas;

import static menu.Utils.waitForUserInput;

import java.util.ArrayList;
import java.util.Scanner;

import auth.AuthUtils;
import auth.Auth.Role;
import menu.Utils;

public abstract class User {

    protected String id;
    protected String name;
    protected String username;
    protected String password;
    protected String phoneNumber;
    protected Role role;

    // Constructor
    public User(String id, String name, String username, String password, String phoneNumber, Role role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
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
    public Role getRole() {
        return this.role;
    }

    // Setters
    public void setName(Scanner scanner) {
        String newName = AuthUtils.inputName(scanner);
        this.name = newName;
    }
    public void setUsername(Scanner scanner, ArrayList<User> users) {
        String newUsername = AuthUtils.inputUsername(scanner, users);
        this.username = newUsername;
    }
    public void setPassword(Scanner scanner) {
        String newPassword = AuthUtils.inputPassword(scanner);
        this.password = newPassword;
    }
    public void setPhoneNumber(Scanner scanner) {
        String newPhoneNumber = AuthUtils.inputPhoneNumber(scanner);
        this.phoneNumber = newPhoneNumber;
    }

    // Methods
    public abstract void menu(Scanner scanner, ArrayList<User> users, User loggedUser, ArrayList<Transaction> transactions);

    public static void modifyAccountData(ArrayList<User> users, User currentUser, Scanner scanner) {
		Boolean alive = true;
        Integer changesApplied = 0;

		do {
			Utils.clearScreen();
			System.out.print("=== [ACCOUNT DATA EDIT] ================\n"
				+ "1. [Name]\n"
				+ "2. [Username]\n"
				+ "3. [Password]\n"
				+ "4. [Phone number]\n"
				+ "5. [Exit]\n"
                + "Changes applied: " + changesApplied + "\n"
				+ "\nOption: "
			);
			Integer option = scanner.nextInt();
			scanner.nextLine();

			switch(option) {
				case 1:
					currentUser.setName(scanner);
                    waitForUserInput(scanner);
                    changesApplied++;
					break;
				case 2:
					currentUser.setUsername(scanner, users);
                    waitForUserInput(scanner);
                    changesApplied++;
					break;
				case 3:
					currentUser.setPassword(scanner);
                    waitForUserInput(scanner);
                    changesApplied++;
					break;
				case 4:
					currentUser.setPhoneNumber(scanner);
                    waitForUserInput(scanner);
                    changesApplied++;
					break;
				case 5:
					alive = false;
					break;
				default:
					System.out.println("[Invalid option]");
                    waitForUserInput(scanner);
					break;
			}
		} while (alive);

		System.out.println("\n[Account data updated]");
	}
}
