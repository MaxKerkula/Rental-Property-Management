package com.techelevator.service;

import com.techelevator.model.UserDetails;
import com.techelevator.model.Users;

import java.security.Principal;
import java.util.List;

public interface UserService {
    Users getUserById(int id);

    boolean create(String username, String password, String role);

    boolean updateUser(int id, Users user);

    void deleteUser(int id);

    List<Users> findAll();

    UserDetails getLoggedInUserDetails(Principal principal);

    UserDetails createUserDetails(int userId, UserDetails userDetails);

    boolean updateUserDetails(int userId, UserDetails userDetails);

    void deleteUserDetails(int userId);

}
