package com.techelevator.dao;

import com.techelevator.model.Users;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class JdbcUserDaoTests extends BaseDaoTests {
    protected static final Users USERS_1 = new Users(1, "user1", "user1", "ROLE_USER");
    protected static final Users USERS_2 = new Users(2, "user2", "user2", "ROLE_USER");
    private static final Users USERS_3 = new Users(3, "user3", "user3", "ROLE_USER");

    private JdbcUserDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcUserDao(jdbcTemplate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findIdByUsername_given_null_throws_exception() {
        sut.findIdByUsername(null);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void findIdByUsername_given_invalid_username_throws_exception() {
        sut.findIdByUsername("invalid");
    }

    @Test
    public void findIdByUsername_given_valid_user_returns_user_id() {
        int actualUserId = sut.findIdByUsername(USERS_1.getUsername());

        Assert.assertEquals(USERS_1.getId(), actualUserId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByUsername_given_null_throws_exception() {
        sut.findByUsername(null);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void findByUsername_given_invalid_username_throws_exception() {
        sut.findByUsername("invalid");
    }

    @Test
    public void findByUsername_given_valid_user_returns_user() {
        Users actualUsers = sut.findByUsername(USERS_1.getUsername());

        Assert.assertEquals(USERS_1, actualUsers);
    }

    public void getUserById_given_invalid_user_id_returns_null() {
        Users users = sut.getUserById(-1);

        Assert.assertNull(users);
    }

    @Test
    public void getUserById_given_valid_user_id_returns_user() {
        Users actualUsers = sut.getUserById(USERS_1.getId());

        Assert.assertEquals(USERS_1, actualUsers);
    }

    @Test
    public void findAll_returns_all_users() {
        List<Users> users = sut.findAll();

        Assert.assertNotNull(users);
        Assert.assertEquals(3, users.size());
        Assert.assertEquals(USERS_1, users.get(0));
        Assert.assertEquals(USERS_2, users.get(1));
        Assert.assertEquals(USERS_3, users.get(2));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void create_user_with_null_username() {
        sut.create(null, USERS_3.getPassword(), "ROLE_USER");
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void create_user_with_existing_username() {
        sut.create(USERS_1.getUsername(), USERS_3.getPassword(), "ROLE_USER");
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_user_with_null_password() {
        sut.create(USERS_3.getUsername(), null, "ROLE_USER");
    }

    @Test
    public void create_user_creates_a_user() {
        Users newUsers = new Users(-1, "new", "user", "ROLE_USER");

        boolean userWasCreated = sut.create(newUsers.getUsername(), newUsers.getPassword(), "ROLE_USER");

        Assert.assertTrue(userWasCreated);

        Users actualUsers = sut.findByUsername(newUsers.getUsername());
        newUsers.setId(actualUsers.getId());

        actualUsers.setPassword(newUsers.getPassword()); // reset password back to unhashed password for testing
        Assert.assertEquals(newUsers, actualUsers);
    }
}
