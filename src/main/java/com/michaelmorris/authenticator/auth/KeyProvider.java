package com.michaelmorris.authenticator.auth;

import com.auth0.jwt.interfaces.RSAKeyProvider;

public interface KeyProvider extends RSAKeyProvider {

    String getPublicKeyEncodedAsText();

}
