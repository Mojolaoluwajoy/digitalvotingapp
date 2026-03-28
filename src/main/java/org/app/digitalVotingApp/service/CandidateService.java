package org.app.digitalVotingApp.service;

import org.app.digitalVotingApp.data.dtos.requests.CandidateRegistrationRequest;
import org.app.digitalVotingApp.data.dtos.responses.CandidatesResponse;
import org.app.digitalVotingApp.data.dtos.responses.UserResponse;
import org.app.digitalVotingApp.exceptions.CandidateAlreadyExistException;
import org.app.digitalVotingApp.exceptions.CandidateNotFoundException;

import java.util.List;

public interface CandidateService {
    CandidatesResponse completeRegistration(CandidateRegistrationRequest candidateRegistrationRequest);

}