package com.michaelmorris.authenticator.auth;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
public class AuthKeyProvider implements KeyProvider {

    @Override
    public RSAPublicKey getPublicKeyById(String s) {
        return null;
    }

    @Override
    public RSAPrivateKey getPrivateKey() {
        return null;
    }

    @Override
    public String getPrivateKeyId() {
        return null;
    }

}
