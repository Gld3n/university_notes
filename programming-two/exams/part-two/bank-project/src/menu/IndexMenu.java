package menu;

import static menu.Utils.waitForUserInput;

import java.util.ArrayList;
import java.util.Scanner;

import auth.Auth;
import schemas.*;

public class IndexMenu {

	static ArrayList<User> users = new ArrayList<User>();
	static ArrayList<Transaction> transactions = new ArrayList<Transaction>();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean alive = true;
		
		do {
			Utils.clearScreen();
			System.out.print("=== [MAIN MENU] ========================\n"
				+ "1. [Sign up]\n"
				+ "2. [Login]\n"
				+ "3. [Exit]\n"
				+ "\nOption: "
			);
			Integer option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
				case 1:
					Auth.signUp(scanner, users);
					waitForUserInput(scanner);
					break;
				case 2:
					Auth.login(scanner, users, transactions);
					waitForUserInput(scanner);
					break;
				case 3:
					System.out.println("[Exiting...]");
					alive = false;
					break;
				default:
					System.out.println("[Invalid option]");
					waitForUserInput(scanner);
					break;
			}
		} while (alive);

		scanner.close();
	}
}
