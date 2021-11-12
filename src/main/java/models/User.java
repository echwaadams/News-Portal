package models;

public class User {
    private int id;
    private String name;
    private String position;
    private String role;
    private int departmentId;

    public User(int id, String position, String name, String role, int departmentId) {
        this.id = id;
        this.departmentId = departmentId;
        this.role = role;
        this.position = position;
        this.name = name;
    }
}
