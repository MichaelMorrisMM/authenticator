package com.michaelmorris.authenticator.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class AuthServiceTests {

    @Autowired
    private AuthenticationService authenticationService;

    @Test
    void test() {
    }

}
