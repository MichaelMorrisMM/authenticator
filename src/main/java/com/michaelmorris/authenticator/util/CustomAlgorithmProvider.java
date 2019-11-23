package com.michaelmorris.authenticator.util;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomAlgorithmProvider implements AlgorithmProvider {

    private final KeyProvider keyProvider;

    @Autowired
    public CustomAlgorithmProvider(KeyProvider keyProvider) {
        this.keyProvider = keyProvider;
    }

    @Override
    public Algorithm getAlgorithm() {
        return Algorithm.RSA256(keyProvider.getPublicKeyById(null), keyProvider.getPrivateKey());
    }

}
