package auth;

import java.util.ArrayList;
import java.util.Scanner;

import menu.Utils;
import schemas.*;


public class Auth {

    public static enum Role {
        OPERATOR,
        CLIENT
    }
    
    public static void signUp(Scanner scanner, ArrayList<User> users) {
        Utils.clearScreen();
        System.out.print("=== [SIGN UP] ==========================\n");

        String id;
        String name;
        String username;
        String password;
        String phoneNumber;
        Role role;

        id = AuthUtils.inputId(scanner, users);
        name = AuthUtils.inputName(scanner);
        username = AuthUtils.inputUsername(scanner, users);
        password = AuthUtils.inputPassword(scanner);
        phoneNumber = AuthUtils.inputPhoneNumber(scanner);
        role = AuthUtils.selectUserRole(scanner);

        switch (role) {
            case OPERATOR:
                Operator operator = new Operator(id, name, username, password, phoneNumber);
                users.add(operator);
                break;
            case CLIENT:
                Client client = new Client(id, name, username, password, phoneNumber);
                users.add(client);
                break;
            default:
                System.out.println("[Invalid role]");
                return;
        }

        System.out.println("\n[User created successfully]");
    }

    public static void login(Scanner scanner, ArrayList<User> users) {
        Utils.clearScreen();
		System.out.print("=== [LOGIN] ============================");

        if (users.isEmpty()) {
            System.out.println("\n[No users registered]");
            return;
        }

        System.out.print("\nUsername: ");
		String username = scanner.nextLine().toLowerCase().strip();
		System.out.print("Password: ");
		String password = scanner.nextLine();

		User user = AuthUtils.authUser(username, password, users);

		if (user == null) {
			System.out.println("[Invalid username or password]");
            return;
		}

        if (user instanceof Operator) {
            user.menu(scanner, users, (Operator) user);
        } else if (user instanceof Client) {
            user.menu(scanner, users, (Client) user);
        } else {
            System.out.println("[Invalid user]");
        }
	}
}
