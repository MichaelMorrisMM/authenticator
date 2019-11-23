package com.michaelmorris.authenticator.util;

import com.auth0.jwt.interfaces.RSAKeyProvider;

public interface KeyProvider extends RSAKeyProvider {

    String getPublicKeyEncodedAsText();

}
