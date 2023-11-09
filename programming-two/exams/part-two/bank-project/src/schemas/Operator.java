package schemas;

import static menu.Utils.clearScreen;
import static menu.Utils.exchangeRate;
import static menu.Utils.waitForUserInput;

import java.util.ArrayList;
import java.util.Scanner;

import auth.AuthUtils;
import auth.Auth.Role;
import menu.Utils;

public class Operator extends User {

	// Constructor
	public Operator(String id, String name, String username, String password, String phoneNumber, Role role) {
		super(id, name, username, password, phoneNumber, role);
	}

	@Override
	public void menu(Scanner scanner, ArrayList<User> users, User loggedUser, ArrayList<Transaction> transactions) {
		Operator operator = (Operator) loggedUser;
		Boolean alive = true;

		do {
			clearScreen();
			System.out.print("=== [OPERATOR MENU] ===================\n"
			+ "1. [Account data]\n"
			+ "2. [Modify account data]\n"
			+ "3. [List bank users]\n"
			+ "4. [Search user]\n"
			+ "5. [See bank transactions]\n"
			+ "6. [Bank statistics]\n"
			+ "7. [Exit]\n"
			+ "\nOption: "
			);
			Integer option = scanner.nextInt();
			scanner.nextLine();
			
			switch(option) {
				case 1:
					clearScreen();
					showAccountData((Operator) loggedUser);
					waitForUserInput(scanner);
					break;
				case 2:
					clearScreen();
					modifyAccountData(users, operator, scanner);
					waitForUserInput(scanner);
					break;
				case 3:
					clearScreen();
					listBankUsers(users, scanner);
					waitForUserInput(scanner);
					break;
				case 4:
					clearScreen();
					searchUser(users, scanner);
					waitForUserInput(scanner);
					break;
				case 5:
					clearScreen();
					showTransactions(transactions);
					waitForUserInput(scanner);
					break;
				case 6:
					clearScreen();
					bankStatistics(transactions, users);
					waitForUserInput(scanner);
					break;
				case 7:
					System.out.println("[Exiting...]");
					alive = false;
					break;
			}
		} while (alive);
	}

	public static void showAccountData(Operator currentOperator) {
		System.out.println("=== [ACCOUNT DATA] =====================\n"
			+ "ID: " + currentOperator.getId() + "\n"
			+ "Name: " + currentOperator.getName() + "\n"
			+ "Username: " + currentOperator.getUsername() + "\n"
			+ "Phone number: " + currentOperator.getPhoneNumber() + "\n"
			+ "Role: " + currentOperator.getRole() + "\n"
		);
	}

	public static void listBankUsers(ArrayList<User> users, Scanner scanner) {
		System.out.println("=== [BANK USERS] =======================\n");
		for (User user : users) {
			System.out.println(
				"ID: " + user.getId() + "\n"
				+ "Name: " + user.getName() + "\n"
				+ "----------------------------------------"
			);
		}

		System.out.print("Select a user by ID to detail ('q' to quit): ");
		String userId = scanner.nextLine().toLowerCase().strip();
		if (userId.equals("q")) {
			return;
		}

		if (AuthUtils.findUserById(userId, users) != null) {
			User user = AuthUtils.findUserById(userId, users);
			System.out.println("=== [USER DETAILS] =====================\n"
				+ "ID: " + user.getId() + "\n"
				+ "Name: " + user.getName() + "\n"
				+ "Username: " + user.getUsername() + "\n"
				+ "Phone number: " + user.getPhoneNumber() + "\n"
				+ "Role: " + user.getRole() + "\n"
			);
		} else {
			System.out.println("[User not found]");
		}
	}

	public static void searchUser(ArrayList<User> users, Scanner scanner) {
		System.out.print("=== [SEARCH USER] ======================\n"
			+ "1. [By ID]\n"
			+ "2. [By username]\n"
			+ "3. [Exit]\n"
			+ "\nOption: "
		);
		Integer option = scanner.nextInt();
		scanner.nextLine();

		switch(option) {
			case 1:
				System.out.print("[ID]: ");
				String id = scanner.nextLine();
				if (AuthUtils.findUserById(id, users) != null) {
					User user = AuthUtils.findUserById(id, users);
					clearScreen();
					System.out.println("=== [USER DETAILS] =====================\n"
						+ "ID: " + user.getId() + "\n"
						+ "Name: " + user.getName() + "\n"
						+ "Username: " + user.getUsername() + "\n"
						+ "Phone number: " + user.getPhoneNumber() + "\n"
						+ "Role: " + user.getRole()
					);
				} else {
					System.out.println("[User not found]");
				}
				break;
			case 2:
				System.out.println("[Username]: ");
				String username = scanner.nextLine().toLowerCase().strip();
				if (AuthUtils.findUserByUsername(username, users) != null) {
					User user = AuthUtils.findUserByUsername(username, users);
					System.out.println("=== [USER DETAILS] =====================\n"
						+ "ID: " + user.getId() + "\n"
						+ "Name: " + user.getName() + "\n"
						+ "Username: " + user.getUsername() + "\n"
						+ "Phone number: " + user.getPhoneNumber() + "\n"
						+ "Role: " + user.getRole() + "\n"
					);
				} else {
					System.out.println("[User not found]");
				}
				break;
			default:
				System.out.println("[Invalid option]");
				break;
		}
	}

	public static void showTransactions(ArrayList<Transaction> transactions) {
		System.out.println("=== [BANK TRANSACTIONS] ================\n");

		if (transactions.isEmpty()) {
			System.out.println("[Data not available]");
			return;
		}

		for (Transaction transaction : transactions) {
			System.out.println(transaction.toString());
		}
	}

	public static void bankStatistics(ArrayList<Transaction> transactions, ArrayList<User> users) {
		if (transactions.isEmpty() || users.isEmpty()) {
			System.out.println("[Data not available. Not enough users or transactions]");
			return;
		}

		String idLowestBalance = "";
		String idHighestBalance = "";
		Double highestBalance = 0.0;
		Double lowestBalance = 0.0;

		for (User user : users) {
			if (user.getRole() == Role.CLIENT) {
				Client client = (Client) user;

				if (client.getBalance() > highestBalance) {
					highestBalance = client.getBalance();
					idHighestBalance = client.getId();
				} else if (client.getBalance() < lowestBalance) {
					lowestBalance = client.getBalance();
					idLowestBalance = client.getId(); 
				}
			}
		}

		System.out.println("=== [BANK STATISTICS] ==================\n");
		System.out.println("Total transactions: " + transactions.size() + "\n");
		System.out.println("Highest balance: $" + highestBalance + " | BsS." + (highestBalance * exchangeRate) + " (ID: " + idHighestBalance + ")");
		System.out.println("Lowest balance: $" + lowestBalance + " | BsS." + (lowestBalance * exchangeRate) + " (ID: " + idLowestBalance + ")");
	}
}
