package com.techelevator.dao;

import com.techelevator.model.Authority;
import com.techelevator.model.Users;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class JdbcUserDao implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int findIdByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");

        int userId;
        try {
            userId = jdbcTemplate.queryForObject("select user_id from users where username = ?", int.class, username);
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("Users " + username + " was not found.");
        }

        return userId;
    }

    @Override
    public boolean create(String username, String password, String role) {
        String insertUserSql = "insert into users (username,password_hash,role) values (?,?,?)";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        String ssRole = role.toUpperCase().startsWith("ROLE_") ? role.toUpperCase() : "ROLE_" + role.toUpperCase();

        return jdbcTemplate.update(insertUserSql, username, password_hash, ssRole) == 1;
    }

    @Override
    public Users getUserById(int userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()) {
            return mapRowToUser(results);
        } else {
            return null;
        }
    }

    @Override
    public List<Users> findAll() {
        List<Users> users = new ArrayList<>();
        String sql = "select * from users";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Users user = mapRowToUser(results);
            users.add(user);
        }

        return users;
    }

    @Override
    public Users findByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");

        for (Users users : this.findAll()) {
            if (users.getUsername().equalsIgnoreCase(username)) {
                return users;
            }
        }
        throw new UsernameNotFoundException("Users " + username + " was not found.");
    }

    @Override
    public String getUserRoleByID(int ID) {
        String sql = "SELECT role FROM users WHERE user_id = ?";
        String userRole = jdbcTemplate.queryForObject(sql, String.class, ID);
        return userRole;
    }

    @Override
    public boolean updateUser(int id, Users user) {
        String sql = "UPDATE users SET username = ?, password_hash = ?, role = ? WHERE user_id = ?";
        String passwordHash = new BCryptPasswordEncoder().encode(user.getPassword());
        String authorities = String.join(",", user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toList()));
        return jdbcTemplate.update(sql, user.getUsername(), passwordHash, authorities, id) == 1;
    }


    @Override
    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        jdbcTemplate.update(sql, id);
    }

    private Users mapRowToUser(SqlRowSet rs) {
        Users users = new Users();
        users.setId(rs.getInt("user_id"));
        users.setUsername(rs.getString("username"));
        users.setPassword(rs.getString("password_hash"));
        users.setAuthorities(Objects.requireNonNull(rs.getString("role")));
        users.setActivated(true);
        return users;
    }
}
