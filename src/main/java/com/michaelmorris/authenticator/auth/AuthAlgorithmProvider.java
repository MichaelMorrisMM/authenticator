package com.michaelmorris.authenticator.auth;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthAlgorithmProvider implements AlgorithmProvider {

    private final KeyProvider keyProvider;

    @Autowired
    public AuthAlgorithmProvider(KeyProvider keyProvider) {
        this.keyProvider = keyProvider;
    }

    @Override
    public Algorithm getAlgorithm() {
        return Algorithm.RSA256(keyProvider.getPublicKeyById(null), keyProvider.getPrivateKey());
    }

}
