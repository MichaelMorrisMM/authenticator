package com.michaelmorris.authenticator.auth;

import com.michaelmorris.authenticator.model.InvalidCredentialsException;
import com.michaelmorris.authenticator.model.User;
import com.michaelmorris.authenticator.model.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserService implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Iterable<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void registerUser(User user) throws UsernameAlreadyExistsException {
        if (this.userRepository.existsByUsername(user.getUsername())) {
            throw new UsernameAlreadyExistsException("A user with the username " + user.getUsername() + " already exists");
        }
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
    }

    @Override
    public User authenticateUser(User user) throws InvalidCredentialsException {
        return this.userRepository
                .findByUsername(user.getUsername())
                .filter((User candidate) -> this.passwordEncoder.matches(user.getPassword(), candidate.getPassword()))
                .orElseThrow(() -> new InvalidCredentialsException("The provided username/password is invalid"));
    }

}
