package org.app.digitalVotingApp.controller;

import lombok.extern.slf4j.Slf4j;
import org.app.digitalVotingApp.data.dtos.requests.UserRequest;
import org.app.digitalVotingApp.data.dtos.responses.GenericResponse;
import org.app.digitalVotingApp.data.dtos.responses.UserResponse;
import org.app.digitalVotingApp.exceptions.VoterAlreadyExistException;
import org.app.digitalVotingApp.exceptions.VoterNotFoundException;
import org.app.digitalVotingApp.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

  private final UserServiceImpl userService;

  public UserController(UserServiceImpl userService){
      this.userService =userService;
  }

    @PostMapping("/register")
    public ResponseEntity<GenericResponse> registerUser(@RequestBody UserRequest userRequest) throws VoterAlreadyExistException {
      UserResponse savedUser = userService.register(userRequest);
        return new ResponseEntity<>(GenericResponse.success(savedUser, "Registration successful"), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<GenericResponse> getAllUsers(){
        List<UserResponse> users= userService.getAll();
        return new ResponseEntity<>(GenericResponse.success(users,"Users found"),HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity <GenericResponse> findUserById(@PathVariable String id)throws VoterNotFoundException{
        UserResponse userResponses = userService.findById(id);
           return new ResponseEntity<>(GenericResponse.success(userResponses,"User found"),HttpStatus.FOUND );
           }
}
