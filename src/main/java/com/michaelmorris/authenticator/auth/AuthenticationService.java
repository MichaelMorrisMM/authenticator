package com.michaelmorris.authenticator.auth;

public interface AuthenticationService {

    String createToken(String subject);

}
