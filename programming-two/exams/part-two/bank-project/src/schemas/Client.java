package schemas;

import java.util.ArrayList;
import java.util.Scanner;

public class Client extends User {
	
	private final String role = "Client";
	private Double balance = 0.0;

	// Constructor
	public Client(String id, String name, String username, String password, String phoneNumber) {
		super(id, name, username, password, phoneNumber);
	}

	// Getters
	public String getRole() {
		return this.role;
	}

	public Double getBalance() {
		return this.balance;
	}

	@Override
	public void menu(Scanner scanner, ArrayList<User> users, User loggedUser) {
		
		System.out.print("=== [CLIENT MENU] =====================\n"
			+ "1. [Account data]\n"
			+ "2. [Modify account data]\n"
			+ "3. [Transfer money]\n"
			+ "4. [Deposit money]\n"
			+ "5. [Withdraw money]\n"
			+ "6. [Exit]\n"
			+ "\nOption: "
		);
		Integer option = scanner.nextInt();
		scanner.nextLine();
		
		switch(option) {
			case 1:
				showAccountData(users, (Client) loggedUser);
				break;
			case 2:
				System.out.println("[Modify account data]");
				break;
			case 3:
				System.out.println("[Transfer money]");
				break;
			case 4:
				System.out.println("[Deposit money]");
				break;
			case 5:
				System.out.println("[Withdraw money]");
				break;
			case 6:
				System.out.println("[Exiting...]");
				break;
		}
	}

	// Menu methods
	public static void showAccountData(ArrayList<User> users, Client currentUser) {
		System.out.println("[Account data]");
	}

	public static void modifyAccountData(ArrayList<User> users, Client currentUser) {
		System.out.println("[Modify account data]");
	}

	public static void transfer(ArrayList<User> users, Client currentUser) {
		System.out.println("[Transfer money]");
	}

	public static void deposit(ArrayList<User> users, Client currentUser) {
		System.out.println("[Deposit money]");
	}

	public static void withdraw(ArrayList<User> users, Client currentUser) {
		System.out.println("[Withdraw money]");
	}
}
