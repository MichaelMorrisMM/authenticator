package com.michaelmorris.authenticator.auth;

import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class AuthService implements AuthenticationService {

    private final AlgorithmProvider algorithmProvider;
    private final PasswordEncoder passwordEncoder;

    private static final String ISSUER = "authenticator";

    @Autowired
    public AuthService(AlgorithmProvider algorithmProvider, PasswordEncoder passwordEncoder) {
        this.algorithmProvider = algorithmProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String createToken(String subject) {
        Instant now = Instant.now();
        return JWT.create()
                .withSubject(subject)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(now.plus(1, ChronoUnit.DAYS)))
                .withIssuer(ISSUER)
                .sign(algorithmProvider.getAlgorithm());
    }

}
