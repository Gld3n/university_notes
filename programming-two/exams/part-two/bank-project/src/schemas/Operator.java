package schemas;

import java.util.ArrayList;
import java.util.Scanner;

public class Operator extends User {

	private final String role = "Operator";

	// Constructor
	public Operator(String id, String name, String username, String password, String phoneNumber) {
		super(id, name, username, password, phoneNumber);
	}

	// Getters
	public String getRole() {
		return this.role;
	}

	@Override
	public void menu(Scanner scanner, ArrayList<User> users, User loggedUser) {
		
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
				// showAccountData(users, loggedUser);
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
				System.out.println("[Bank statistics]");
				break;
			case 7:
				System.out.println("[Exiting...]");
				break;
		}
	}
}
