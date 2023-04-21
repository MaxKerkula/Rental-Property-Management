package com.techelevator.controller;

import com.techelevator.model.UserDetails;
import com.techelevator.model.Users;
import com.techelevator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Users> getUserById(@PathVariable int id) {
        Users user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    boolean create(String username, String password, String role) {
        return userService.create(username, password, role);
    }

    @DeleteMapping("delete/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @GetMapping("")
    public ResponseEntity<List<Users>> findAll() {
        List<Users> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // UserDetails Endpoints

    @PostMapping("user-details/")
    public ResponseEntity<UserDetails> createUserDetails(@RequestBody UserDetails userDetails) {
        UserDetails createdUserDetails = userService.createUserDetails(userDetails.getUserId(), userDetails);
        return new ResponseEntity<>(createdUserDetails, HttpStatus.CREATED);
    }

    // UserController.java
    @PutMapping("user-details/update/{userId}")
    public ResponseEntity<Boolean> updateUserDetails(@PathVariable int userId, @RequestBody UserDetails userDetails) {
        boolean updated = userService.updateUserDetails(userId, userDetails);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("user-details/delete/{userId}")
    public ResponseEntity<Void> deleteUserDetails(@PathVariable int userId) {
        userService.deleteUserDetails(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("user-details/current-user")
    public ResponseEntity<UserDetails> getLoggedInUserDetails(Principal principal) {
        UserDetails userDetails = userService.getLoggedInUserDetails(principal);
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }
}
