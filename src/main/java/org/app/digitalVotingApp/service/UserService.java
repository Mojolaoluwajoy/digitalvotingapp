package org.app.digitalVotingApp.service;

import org.app.digitalVotingApp.data.dtos.requests.UserRequest;
import org.app.digitalVotingApp.data.dtos.requests.VotersRegistrationRequest;
import org.app.digitalVotingApp.data.dtos.responses.UserResponse;
import org.app.digitalVotingApp.exceptions.VoterAlreadyExistException;
import org.app.digitalVotingApp.exceptions.VoterNotFoundException;

import java.util.List;

public interface UserService {
UserResponse register(UserRequest userRequest)throws VoterAlreadyExistException,VoterNotFoundException ;

  UserResponse findById(String votersId)throws Exception;
          ;

    List<UserResponse> getAll();

  }
