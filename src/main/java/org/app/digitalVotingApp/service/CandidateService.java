package org.app.digitalVotingApp.service;

import org.app.digitalVotingApp.data.dtos.requests.CandidateRegistrationRequest;
import org.app.digitalVotingApp.data.dtos.responses.CandidatesResponse;
import org.app.digitalVotingApp.exceptions.CandidateAlreadyExistException;
import org.app.digitalVotingApp.exceptions.CandidateNotFoundException;

import java.util.List;

public interface CandidateService {
  CandidatesResponse register(CandidateRegistrationRequest candidateRegistrationRequest)throws CandidateAlreadyExistException;


    List<CandidatesResponse> getAll();

    CandidatesResponse getCandidateById(String id)throws CandidateNotFoundException;
}
