package schemas;

import static menu.Utils.waitForUserInput;

import java.util.ArrayList;
import java.util.Scanner;

import schemas.Transaction.Operation;
import auth.AuthUtils;
import auth.Auth.Role;
import menu.Utils;

public class Client extends User {
	private Double balance = 0.0;

	// Constructor
	public Client(String id, String name, String username, String password, String phoneNumber, Role role) {
		super(id, name, username, password, phoneNumber, role);
	}

	// Getters
	public Double getBalance() {
		return this.balance;
	}

	// Setters
	public void setBalance(Double amount, Operation operation) {
		switch (operation) {
			case DEPOSIT:
				this.balance += amount;
				break;
			case WITHDRAW:
				this.balance -= amount;
				break;
			default:
				System.out.println("[Invalid operation]");
				break;
		}
	}

	// Methods
	@Override
	public void menu(Scanner scanner, ArrayList<User> users, User loggedUser, ArrayList<Transaction> transactions) {
		Client client = (Client) loggedUser;
		Boolean alive = true;

		do {
			Utils.clearScreen();
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
					Utils.clearScreen();
					showAccountData(client);
					waitForUserInput(scanner);
					break;
				case 2:
					modifyAccountData(users, client, scanner);
					waitForUserInput(scanner);
					break;
				case 3:
					Utils.clearScreen();
					transfer(users, client, scanner, transactions);
					waitForUserInput(scanner);
					break;
				case 4:
					Utils.clearScreen();
					deposit(client, scanner, transactions);
					waitForUserInput(scanner);
					break;
				case 5:
					Utils.clearScreen();
					withdraw(client, scanner, transactions);
					waitForUserInput(scanner);
					break;
				case 6:
					System.out.println("[Exiting...]");
					alive = false;
					break;
			}
		} while (alive);
	}

	// Client methods
	public static void showAccountData(Client currentClient) {
		String formatedBalance = String.format("[$%.2f | BsS.%.2f]", currentClient.getBalance(), currentClient.getBalance() * Utils.exchangeRate);

		System.out.println("=== [ACCOUNT DATA] =====================\n"
			+ "ID: " + currentClient.getId() + "\n"
			+ "Name: " + currentClient.getName() + "\n"
			+ "Username: " + currentClient.getUsername() + "\n"
			+ "Phone number: " + currentClient.getPhoneNumber() + "\n"
			+ "Balance:" + formatedBalance
		);
	}

	public static void transfer(ArrayList<User> users, Client currentClient, Scanner scanner, ArrayList<Transaction> transactions) {
		System.out.println("=== [TRANSFER] =========================\n");
		
		if (users.size() < 2) {
			System.out.println("[There are no other users]");
			return;
		}

		if (currentClient.getBalance() == 0.0) {
			System.out.println("[You have no money to transfer]");
			return;
		}

		Client targetClient = null;
		Double amount = 0.0;
		
		// Target client ID validation
		do {
			System.out.print("[ID]: ");
			String id = scanner.nextLine();
			targetClient = (Client) AuthUtils.findUserById(id, users);

			if (targetClient == null) {
				System.out.println("[User not found]");
				continue;
			} else if (targetClient.getId().equals(currentClient.getId())) {
				System.out.println("[You can't transfer money to yourself]");
				continue;
			}

			break;
		} while (true);

		// Target client phone number validation
		do {
			System.out.print("[Phone number]: ");
			String phoneNumber = scanner.nextLine();
			if (!targetClient.getPhoneNumber().equals(phoneNumber)) {
				System.out.println("[Invalid phone number]");
				continue;
			}

			break;
		} while (true);

		// Amount validation
		do {
			System.out.print("[Amount]: $");
			amount = scanner.nextDouble();
			scanner.nextLine();

			if (amount > currentClient.getBalance()) {
				System.out.println("[Insufficient funds]");
				continue;
			} else if (amount < 0.1) {
				System.out.println("[Invalid amount]");
				continue;
			}

			
			System.out.print("[Confirm transfer to " + targetClient.getName() + " (y/n)]: ");
			do {
				String confirm = scanner.nextLine().toLowerCase().strip();

				if (confirm.equals("y")) {
					break;
				} else if (confirm.equals("n")) {
					return;
				} else {
					System.out.println("[Invalid option]");
					continue;
				}
			} while (true);

			break;
		} while (true);

		currentClient.setBalance(amount, Operation.WITHDRAW);
		targetClient.setBalance(amount, Operation.DEPOSIT);
		transactions.add(new Transaction(currentClient.getName(), currentClient.getId(), amount, Operation.TRANSFER, targetClient.getId()));
		System.out.println("[Transfer completed]");
	}

	public static void deposit(Client currentClient, Scanner scanner, ArrayList<Transaction> transactions) {
		System.out.println("=== [DEPOSIT] ==========================\n");

		System.out.print("[Amount]: $");
		Double amount = scanner.nextDouble();
		scanner.nextLine();
		if (amount < 0.1) {
			System.out.println("[Invalid amount]");
			return;
		}

		currentClient.setBalance(amount, Operation.DEPOSIT);
		transactions.add(new Transaction(currentClient.getName(), currentClient.getId(), amount, Operation.DEPOSIT, null));
		System.out.println("[Deposit completed]");
	}

	public static void withdraw(Client currentClient, Scanner scanner, ArrayList<Transaction> transactions) {
		System.out.println("=== [WITHDRAW] =========================\n");

		if (currentClient.getBalance() == 0.0) {
			System.out.println("[You have no money to withdraw]");
			return;
		}

		System.out.print("[Amount]: $");
		Double amount = scanner.nextDouble();
		scanner.nextLine();

		if (amount > currentClient.getBalance()) {
			System.out.println("[Insufficient funds]");
			return;
		} else if (amount < 0.1) {
			System.out.println("[Invalid amount]");
			return;
		}

		currentClient.setBalance(amount, Operation.WITHDRAW);
		transactions.add(new Transaction(currentClient.getName(), currentClient.getId(), amount, Operation.WITHDRAW, null));
		System.out.println("[Withdraw completed]");
	}
}
