package com.michaelmorris.authenticator.auth;

import com.auth0.jwt.JWT;
import com.michaelmorris.authenticator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class AuthService implements AuthenticationService {

    private final AlgorithmProvider algorithmProvider;

    private static final String ISSUER = "authenticator";

    @Autowired
    public AuthService(AlgorithmProvider algorithmProvider) {
        this.algorithmProvider = algorithmProvider;
    }

    @Override
    public String createToken(User user) {
        Instant now = Instant.now();
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(now.plus(1, ChronoUnit.DAYS)))
                .withIssuer(ISSUER)
                .sign(algorithmProvider.getAlgorithm());
    }

}
