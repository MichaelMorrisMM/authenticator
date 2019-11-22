package com.michaelmorris.authenticator.auth;

import com.michaelmorris.authenticator.model.User;

public interface AuthenticationService {

    String createToken(User user);

}
