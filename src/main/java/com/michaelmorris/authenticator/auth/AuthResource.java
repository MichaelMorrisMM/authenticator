package com.michaelmorris.authenticator.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthResource {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthResource(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Authenticator is running");
    }
}
