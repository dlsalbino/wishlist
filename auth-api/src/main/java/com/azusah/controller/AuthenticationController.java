package com.azusah.controller;

import com.azusah.controller.response.TokenResponse;
import com.azusah.service.AuthenticationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthenticationController {

    private static final Logger log = LogManager.getLogger(AuthenticationController.class);
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<TokenResponse> login(Authentication authentication) {
        log.info("START: Token request");
        String token = authenticationService.authenticate(authentication);
        log.info("END: Token request");
        return new ResponseEntity<>(new TokenResponse(token), HttpStatus.OK);
    }
}
