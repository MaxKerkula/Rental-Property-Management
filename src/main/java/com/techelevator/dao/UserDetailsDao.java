package com.techelevator.dao;

import com.techelevator.model.UserDetails;

import java.security.Principal;
import java.util.List;

public interface UserDetailsDao {


    UserDetails getLoggedInUserDetails(int userId);

    UserDetails createUserDetails(int userId, UserDetails userDetails);

    boolean updateUserDetails(int id, UserDetails userDetails);

    void deleteUserDetails(int id);

}
