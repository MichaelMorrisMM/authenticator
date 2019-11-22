package com.michaelmorris.authenticator.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthResource {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final KeyProvider keyProvider;

    @Autowired
    public AuthResource(AuthenticationService authenticationService, UserService userService, KeyProvider keyProvider) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.keyProvider = keyProvider;
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Authenticator is running");
    }

    @GetMapping("/public-key")
    public ResponseEntity<String> publicKey() {
        return ResponseEntity.ok(this.keyProvider.getPublicKeyEncodedAsText());
    }

}
