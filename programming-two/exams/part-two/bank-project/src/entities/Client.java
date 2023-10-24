package entities;

public class Client extends User {
	
	final String role = "Client";

	// Constructor
	public Client(String id, String name, String username, String password, String phoneNumber) {
		super(id, name, username, password, phoneNumber);
	}
}
