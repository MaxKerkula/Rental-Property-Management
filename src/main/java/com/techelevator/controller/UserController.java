package com.techelevator.controller;

import com.techelevator.model.Users;
import com.techelevator.model.UserDetails;
import com.techelevator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    // User Endpoints
    @GetMapping("/{id}")
    public Users getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    boolean create(String username, String password, String role) {
        return userService.create(username, password, role);
    }

    @DeleteMapping("delete/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @GetMapping("")
    public List<Users> findAll() {
        return userService.findAll();
    }

    // UserDetails Endpoints

    @PostMapping("user-details/")
    public UserDetails createUserDetails(@RequestBody UserDetails userDetails) {
        return userService.createUserDetails(userDetails.getUserId(), userDetails);
    }

    // UserController.java
    @PutMapping("user-details/update/{userId}")
    public boolean updateUserDetails(@PathVariable int userId, @RequestBody UserDetails userDetails) {
        return userService.updateUserDetails(userId, userDetails);
    }

    @DeleteMapping("user-details/delete/{userId}")
    public void deleteUserDetails(@PathVariable int userId) {
        userService.deleteUserDetails(userId);
    }

    @GetMapping("user-details/current-user")
    public UserDetails getLoggedInUserDetails(Principal principal) {
        return userService.getLoggedInUserDetails(principal);
    }
}
