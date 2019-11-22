package com.michaelmorris.authenticator.auth;

import com.michaelmorris.authenticator.model.InvalidCredentialsException;
import com.michaelmorris.authenticator.model.User;
import com.michaelmorris.authenticator.model.UsernameAlreadyExistsException;

import java.util.Optional;

public interface UserService {

    Iterable<User> findAllUsers();
    Optional<User> findUserById(Long id);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUsername(String username);
    void registerUser(User user) throws UsernameAlreadyExistsException;
    User authenticateUser(User user) throws InvalidCredentialsException;

}
