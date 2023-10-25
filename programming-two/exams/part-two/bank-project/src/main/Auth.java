package main;

import java.util.Scanner;
import java.util.ArrayList;

import entities.*;

public class Auth {

    enum Role {
        OPERATOR,
        CLIENT
    }
    
    public static void signUp(Scanner scanner, ArrayList<User> users) {
        System.out.print("=== [SIGN UP] ==========================\n"
            + "ID: "
        );
        String id = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Role: ");
        Role role = selectUserRole(scanner);

        if (role == null) {
            return;
        }

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
                break;
        }

    }

    public static void login(Scanner scanner, ArrayList<User> users) {
		System.out.print("=== [LOGIN] ============================\n"
			+ "Username: "
		);
		String username = scanner.nextLine();
		System.out.print("Password: ");
		String password = scanner.nextLine();

		User user = findUser(username, password, users);

		if (user == null) {
			System.out.println("[Invalid username or password]");
		}

		System.out.println("[Welcome, " + user.getName() + "]");
	}

	private static User findUser(String username, String password, ArrayList<User> users) {
		for (User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

    private static Role selectUserRole(Scanner scanner) {
        for (Role role : Role.values()) {
            System.out.println((role.ordinal() + 1) + ". " + role.name());
        }
        System.out.print("Option: ");
        Integer selectedRole = scanner.nextInt();
        scanner.nextLine();
        
        switch (selectedRole) {
            case 1:
                return Role.OPERATOR;
            case 2:
                return Role.CLIENT;
            default:
                System.out.println("[Invalid option]");
                return null;
        }
    } 
}
