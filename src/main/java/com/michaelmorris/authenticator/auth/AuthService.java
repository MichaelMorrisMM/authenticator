package com.michaelmorris.authenticator.auth;

import com.auth0.jwt.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthenticationService {

    private final AlgorithmProvider algorithmProvider;

    private static final String ISSUER = "authenticator";

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
