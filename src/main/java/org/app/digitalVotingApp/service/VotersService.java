package org.app.digitalVotingApp.service;

import org.app.digitalVotingApp.data.dtos.requests.VotersRegistrationRequest;
import org.app.digitalVotingApp.data.dtos.responses.VotersResponses;
import org.app.digitalVotingApp.exceptions.VoterAlreadyExistException;
import org.app.digitalVotingApp.exceptions.VoterNotFoundException;

import java.util.List;

public interface VotersService {
VotersResponses register(VotersRegistrationRequest votersRegistrationRequest)throws VoterAlreadyExistException,VoterNotFoundException ;

  VotersResponses findById(String votersId)throws Exception;
          ;

    List<VotersResponses> getAll();

  }
