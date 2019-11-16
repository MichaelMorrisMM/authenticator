package com.michaelmorris.authenticator.auth;

import com.michaelmorris.authenticator.model.User;

import java.util.Optional;

public interface UserService {

    Iterable<User> findAllUsers();
    Optional<User> findUserById(Long id);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUsername(String username);

}
