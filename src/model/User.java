package model;

public class User {

    private String name;
    private UserType type;

    public User(String name, UserType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public UserType getType() {
        return type;
    }
}
