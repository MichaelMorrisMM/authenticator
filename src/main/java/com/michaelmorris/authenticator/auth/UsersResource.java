package com.michaelmorris.authenticator.auth;

import com.michaelmorris.authenticator.model.InvalidCredentialsException;
import com.michaelmorris.authenticator.model.User;
import com.michaelmorris.authenticator.model.UsernameAlreadyExistsException;
import com.michaelmorris.authenticator.security.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class UsersResource {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @Autowired
    public UsersResource(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        this.userService.registerUser(user);
        return ResponseEntity.ok("User created successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(HttpServletResponse response, @RequestBody User user) {
        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + this.authenticationService.createToken(this.userService.authenticateUser(user)));
        return ResponseEntity.ok("User logged in successfully");
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    protected ResponseEntity<String> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    protected ResponseEntity<String> handleInvalidCredentialsException(InvalidCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

}
