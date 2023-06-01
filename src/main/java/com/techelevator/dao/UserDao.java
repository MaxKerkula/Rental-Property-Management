package com.techelevator.dao;

import com.techelevator.model.Users;

import java.security.Principal;
import java.util.List;

public interface UserDao {

    List<Users> findAll();

    Users getUserById(int userId);

    Users findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password, String role);

    String getUserRoleByID(int ID);

    public boolean updateUser(int id, Users user);

    void deleteUser(int id);
}
