package main;

import java.util.ArrayList;
import java.util.Scanner;

import entities.*;

public class IndexMenu {

	static ArrayList<User> users = new ArrayList<User>();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean alive = true;
		
		do {
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
					break;
				case 2:
					Auth.login(scanner, users);
					break;
				case 3:
					System.out.println("[Exiting...]");
					alive = false;
					break;
				default:
					System.out.println("[Invalid option]");
					break;
			}
		} while (alive);

		scanner.close();
	}
}
