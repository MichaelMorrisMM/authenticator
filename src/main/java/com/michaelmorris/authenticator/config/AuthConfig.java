package com.michaelmorris.authenticator.config;

import com.michaelmorris.authenticator.auth.AlgorithmProvider;
import com.michaelmorris.authenticator.auth.AuthAlgorithmProvider;
import com.michaelmorris.authenticator.auth.AuthService;
import com.michaelmorris.authenticator.auth.AuthenticationService;
import com.michaelmorris.authenticator.auth.AuthKeyProvider;
import com.michaelmorris.authenticator.auth.KeyProvider;
import org.springframework.beans.factory.annotation.Autowired;
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
        return new AuthService(this.getAlgorithmProvider());
    }

    @Bean
    @Primary
    public KeyProvider getKeyProvider() {
        return new AuthKeyProvider();
    }

    @Bean
    @Primary
    public AlgorithmProvider getAlgorithmProvider() {
        return new AuthAlgorithmProvider(this.getKeyProvider());
    }

}
