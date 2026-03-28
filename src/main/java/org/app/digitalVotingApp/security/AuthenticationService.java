package org.app.digitalVotingApp.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.app.digitalVotingApp.controller.AuthController;
import org.app.digitalVotingApp.data.dtos.requests.LoginRequest;
import org.app.digitalVotingApp.data.dtos.responses.LoginResponse;
import org.app.digitalVotingApp.data.model.User;
import org.app.digitalVotingApp.data.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final  JwtService jwtService;
    private final UserRepository userRepository;

    public LoginResponse Authenticate(LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword());
   log.info("Authenticating user={}",usernamePasswordAuthenticationToken);

        Authentication authentication= authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        log.info("User authenticated={}",authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Optional< User> user=userRepository.findByEmail(loginRequest.getEmail());
        if (user.isEmpty()) {
            throw new BadCredentialsException("Invalid email or password");
        }
    String token=jwtService.generateToken(user.get());
    return new LoginResponse(user.get(),token);
    }

}
