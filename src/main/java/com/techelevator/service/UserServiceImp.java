package com.techelevator.service;

import com.techelevator.dao.UserDao;
import com.techelevator.dao.UserDetailsDao;
import com.techelevator.model.UserDetails;
import com.techelevator.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserDetailsDao userDetailsDao;

    // User methods
    public List<Users> findAll() {
        return userDao.findAll();
    }

    @Override
    public UserDetails getLoggedInUserDetails(Principal principal) {
        int loggedInUserId = userDao.findIdByUsername(principal.getName());

        return userDetailsDao.getLoggedInUserDetails(loggedInUserId);
    }

    @Override
    public boolean updateUser(int id, Users user){
        return userDao. updateUser( id, user);
    }

    public Users getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    public boolean create(String username, String password, String role) {
        return userDao.create(username, password, role);
    }

    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    // UserDetails methods

    @Override
    public UserDetails createUserDetails(int userId, UserDetails userDetails) {
        return userDetailsDao.createUserDetails(userId, userDetails);
    }
    public boolean updateUserDetails(int userId, UserDetails userDetails) {
        return userDetailsDao.updateUserDetails(userId, userDetails);
    }

    public void deleteUserDetails(int id) {
        userDetailsDao.deleteUserDetails(id);
    }
}
