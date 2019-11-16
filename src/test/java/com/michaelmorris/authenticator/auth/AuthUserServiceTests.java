package com.michaelmorris.authenticator.auth;

import com.michaelmorris.authenticator.model.User;
import com.michaelmorris.authenticator.model.UsernameAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@ActiveProfiles("test")
class AuthUserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    void testRegisterUser() {
        User newUser = new User();
        newUser.setUsername("username");
        newUser.setEmail("email@emails.com");
        newUser.setPassword("password");
        try {
            this.userService.registerUser(newUser);
        } catch (UsernameAlreadyExistsException e) {
            fail("Unique username should not cause UsernameAlreadyExistsException");
        }
        Optional<User> result = this.userService.findUserByUsername("username");
        assertTrue(result.isPresent());
        User savedUser = result.get();
        assertEquals("username", savedUser.getUsername());
        assertEquals("email@emails.com", savedUser.getEmail());
    }

}
