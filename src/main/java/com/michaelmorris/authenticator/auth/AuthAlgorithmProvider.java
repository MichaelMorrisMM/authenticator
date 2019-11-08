package com.michaelmorris.authenticator.auth;

import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthAlgorithmProvider implements AlgorithmProvider {

    private final KeyProvider keyProvider;

    @Override
    public Algorithm getAlgorithm() {
        return Algorithm.RSA256(keyProvider.getPublicKeyById(null), keyProvider.getPrivateKey());
    }

}
