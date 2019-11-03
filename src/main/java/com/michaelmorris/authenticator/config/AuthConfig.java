package com.michaelmorris.authenticator.config;

import com.michaelmorris.authenticator.auth.AuthService;
import com.michaelmorris.authenticator.auth.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"dev", "prod"})
public class AuthConfig {

    @Bean
    @Primary
    public AuthenticationService getAuthenticationService() {
        return new AuthService();
    }

}
