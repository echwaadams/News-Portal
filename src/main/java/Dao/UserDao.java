package Dao;

import models.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    void addUser(User user);
    User findUserById(int id);
    void updateUser(User user, String position, String role, int departmentId);
    void clearAllUsers();
}
