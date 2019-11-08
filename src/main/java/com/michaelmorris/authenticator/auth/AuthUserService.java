package com.michaelmorris.authenticator.auth;

import com.michaelmorris.authenticator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserService implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public AuthUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

}
