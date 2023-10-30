package auth;

import java.util.ArrayList;
import java.util.Scanner;

import auth.Auth.Role;
import schemas.User;

public class AuthUtils {
    
    static User authUser(String username, String password, ArrayList<User> users) {
		for (User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

    public static User findUserByUsername(String username, ArrayList<User> users) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static User findUserById(String id, ArrayList<User> users) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    static Role selectUserRole(Scanner scanner) {
        System.out.print("Role:\n");
        for (Role role : Role.values()) {
            System.out.println("  " + (role.ordinal() + 1) + ". " + role.name());
        }
        System.out.print("  Option: ");
        do {
            Integer selectedRole = scanner.nextInt();
            scanner.nextLine();
            
            switch (selectedRole) {
                case 1:
                    return Role.OPERATOR;
                case 2:
                    return Role.CLIENT;
                default:
                    System.out.println("[Invalid option]");
                    continue;
            }
        } while (true);
    }

    public static String inputId(Scanner scanner, ArrayList<User> users) {
        System.out.print("ID: ");
        do {
            String id = scanner.nextLine();
            if (id.length() < 6 || id.length() > 8) {
                System.out.println("[Invalid ID]");
                continue;
            } else if (users.size() > 0) {
                if (findUserById(id, users) != null) {
                    System.out.println("[ID already exists]");
                    continue;
                }
            }
            return id;
        } while (true);
    }

    public static String inputName(Scanner scanner) {
        System.out.print("Name: ");
        do {
            String name = scanner.nextLine();
            if (name.length() < 2) {
                System.out.println("[Invalid name]");
                continue;
            }
            return name;
        } while (true);
    }

    public static String inputUsername(Scanner scanner, ArrayList<User> users) {
        System.out.print("Username: ");
        do {
            String username = scanner.nextLine().toLowerCase().strip();
            if (username.length() < 2 || username.length() > 16) {
                System.out.println("[Invalid username]");
                continue;
            } else if (users.size() > 0) {
                if (findUserByUsername(username, users) != null) {
                    System.out.println("[Username already exists]");
                    continue;
                }
            }
            return username;
        } while (true);
    }

    public static String inputPassword(Scanner scanner) {
        System.out.print("Password: ");
        do {
            String password = scanner.nextLine();
            if (password.length() < 8) {
                System.out.println("[Invalid password]");
                continue;
            }
            return password;
        } while (true);
    }

    public static String inputPhoneNumber(Scanner scanner) {
        System.out.print("Phone number: ");
        do {
            String phoneNumber = scanner.nextLine();
            if (phoneNumber.length() != 11) {
                System.out.println("[Invalid phone number]");
                continue;
            }
            return phoneNumber;
        } while (true);
    }
}
