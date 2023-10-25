package entities;

public class Client extends User {
	
	private final String role = "Client";

	// Constructor
	public Client(String id, String name, String username, String password, String phoneNumber) {
		super(id, name, username, password, phoneNumber);
	}

	// Getters
	public String getRole() {
		return this.role;
	}
}
