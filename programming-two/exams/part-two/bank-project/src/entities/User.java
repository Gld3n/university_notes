package entities;

public abstract class User {

    private String id;
    private String name;
    private String username;
    private String password;
    private String phoneNumber;

    // Constructor
    public User(String id, String name, String username, String password, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public String getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
