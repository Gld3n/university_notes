package entities;

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
}
