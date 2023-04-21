package com.techelevator.service;

import com.techelevator.dao.UserDao;
import com.techelevator.dao.UserDetailsDao;
import com.techelevator.model.UserDetails;
import com.techelevator.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.security.Principal;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImp.class);
    private UserDao userDao;
    private UserDetailsDao userDetailsDao;

    @Autowired
    public UserServiceImp(UserDao userDao, UserDetailsDao userDetailsDao) {
        this.userDao = userDao;
        this.userDetailsDao = userDetailsDao;
    }

    // User methods
    @Override
    public List<Users> findAll() {
        try {
            return userDao.findAll();
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while retrieving all users", e);
            throw new IllegalStateException("Could not retrieve users.");
        }
    }

    @Override
    public UserDetails getLoggedInUserDetails(Principal principal) {
        int loggedInUserId = userDao.findIdByUsername(principal.getName());
        try {
            return userDetailsDao.getLoggedInUserDetails(loggedInUserId);
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while retrieving logged-in user details", e);
            throw new IllegalStateException("Could not retrieve logged-in user details.");
        }
    }

    @Override
    public boolean updateUser(int id, Users user) {
        try {
            return userDao.updateUser(id, user);
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while updating user", e);
            throw new IllegalArgumentException("Could not update user.");
        }
    }

    public Users getUserById(int userId) {
        try {
            return userDao.getUserById(userId);
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while retrieving user by ID: {}", userId, e);
            throw new IllegalStateException("Could not retrieve user by ID.");
        }
    }

    public boolean create(String username, String password, String role) {
        try {
            return userDao.create(username, password, role);
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while creating user", e);
            throw new IllegalStateException("Could not create user.");
        }
    }

    public void deleteUser(int id) {
        try {
            userDao.deleteUser(id);
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while deleting user with ID: {}", id, e);
            throw new IllegalArgumentException("Could not delete user.");
        }
    }

    // UserDetails methods

    @Override
    public UserDetails createUserDetails(int userId, UserDetails userDetails) {
        try {
            return userDetailsDao.createUserDetails(userId, userDetails);
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while creating user details", e);
            throw new IllegalStateException("Could not create user details.");
        }
    }

    public boolean updateUserDetails(int userId, UserDetails userDetails) {
        try {
            return userDetailsDao.updateUserDetails(userId, userDetails);
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while updating user details", e);
            throw new IllegalArgumentException("Could not update user details.");
        }
    }

    public void deleteUserDetails(int id) {
        try {
            userDetailsDao.deleteUserDetails(id);
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while deleting user details with ID: {}", id, e);
            throw new IllegalArgumentException("Could not delete user details.");
        }
    }
}
