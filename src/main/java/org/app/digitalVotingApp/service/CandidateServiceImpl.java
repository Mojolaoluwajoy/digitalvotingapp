package org.app.digitalVotingApp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.app.digitalVotingApp.data.dtos.requests.CandidateRegistrationRequest;
import org.app.digitalVotingApp.data.dtos.responses.CandidatesResponse;
import org.app.digitalVotingApp.data.dtos.responses.UserResponse;
import org.app.digitalVotingApp.data.model.User;
import org.app.digitalVotingApp.data.repository.UserRepository;
import org.app.digitalVotingApp.exceptions.CandidateAlreadyExistException;
import org.app.digitalVotingApp.exceptions.CandidateNotFoundException;
import org.app.digitalVotingApp.data.model.CandidateProfile;
import org.app.digitalVotingApp.data.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.app.digitalVotingApp.utils.CandidateUtils.*;
@Slf4j
@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService{
@Autowired
    CandidateRepository candidateRepository;
private final PasswordEncoder passwordEncoder;


@Override
    public CandidatesResponse completeRegistration(CandidateRegistrationRequest candidateRegistrationRequest){
        CandidateProfile candidate =map(candidateRegistrationRequest);
       CandidateProfile savedCandidate = candidateRepository.save(candidate);
         
       return map(savedCandidate);

          }
}
