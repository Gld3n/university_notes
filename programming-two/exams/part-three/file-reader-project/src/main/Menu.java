package main;


import java.util.HashMap;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) throws Exception {

		Boolean alive = true;
		Scanner scanner = new Scanner(System.in);
		HashMap<String, Integer> wordsOcurrences = new HashMap<>();

		do {
			Utils.clearScreen();
			System.out.println("=== [MAIN MENU] =======================");
			System.out.print("1. [Insert words to look for]\n" +
							"2. [Show statistics]\n" +
							"3. [Exit]\n" +
							"Option: ");

			Integer option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
				case 1:
					Utils.clearScreen();
					Utils.getWordsToLookFor(scanner, wordsOcurrences);
					Utils.waitForUserInput(scanner);
					break;
				case 2:
					Utils.clearScreen();
					Utils.showStatistics(wordsOcurrences);
					Utils.waitForUserInput(scanner);
					break;
				case 3:
					System.out.println("[Exiting...]");
					alive = false;
					break;
				default:
					System.out.println("[Invalid option]");
					Utils.waitForUserInput(scanner);
					break;
			}
		} while (alive);

		scanner.close();
    }
}