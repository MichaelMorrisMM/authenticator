package com.michaelmorris.authenticator.config;

import com.michaelmorris.authenticator.auth.AuthService;
import com.michaelmorris.authenticator.auth.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestAuthConfig {

    @Bean
    @Primary
    public AuthenticationService getAuthenticationService() {
        return new AuthService();
    }

}
