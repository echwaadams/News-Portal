package models;

import java.util.Objects;

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
    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && departmentId == user.departmentId && Objects.equals(name, user.name) && Objects.equals(position, user.position) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, position, role, departmentId);
    }
}
