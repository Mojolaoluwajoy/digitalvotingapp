package org.app.digitalVotingApp.controller;

import lombok.RequiredArgsConstructor;
import org.app.digitalVotingApp.data.dtos.requests.LoginRequest;
import org.app.digitalVotingApp.data.dtos.responses.GenericResponse;
import org.app.digitalVotingApp.data.dtos.responses.LoginResponse;
import org.app.digitalVotingApp.security.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
private final AuthenticationService authenticationService;

@PostMapping
    public ResponseEntity<GenericResponse> authenticate(@RequestBody LoginRequest loginRequest){
    LoginResponse loginResponse=authenticationService.Authenticate(loginRequest);
    return new ResponseEntity<>(GenericResponse.success(loginResponse,"Authentication successful"), HttpStatus.OK);
}
}
