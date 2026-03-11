package org.app.digitalVotingApp.service;

import org.app.digitalVotingApp.data.dtos.requests.CandidateRegistrationRequest;
import org.app.digitalVotingApp.data.dtos.responses.CandidatesResponse;
import org.app.digitalVotingApp.exceptions.CandidateAlreadyExistException;
import org.app.digitalVotingApp.exceptions.CandidateNotFoundException;
import org.app.digitalVotingApp.model.Candidates;
import org.app.digitalVotingApp.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.app.digitalVotingApp.utils.CandidateUtils.*;

@Service
public class CandidateServiceImpl implements CandidateService{
@Autowired
    CandidateRepository candidateRepository;
@Override
    public CandidatesResponse register(CandidateRegistrationRequest candidateRegistrationRequest)throws CandidateAlreadyExistException{
      String nin=candidateRegistrationRequest.getNin();

      validateExistenceByNin(nin);
        Candidates candidate =map(candidateRegistrationRequest);
         Candidates savedPoll= candidateRepository.createCandidate(candidate);
         
       return map(savedPoll);

          }
          public void validateExistenceByNin(String nin)throws  CandidateAlreadyExistException{
        Candidates foundCandidate=this.findByNin(nin);
        if (foundCandidate!=null){
            throw new CandidateAlreadyExistException(String.format("Candidate with %s nin already exists",nin));
        }

          }

          public Candidates findByNin(String nin){
        for (Candidates candidate : candidateRepository.findAll()){
            if (candidate.getNin().equalsIgnoreCase(nin)){
                return candidate;
            }
        }
        return null;
          }

    @Override
    public CandidatesResponse getCandidateById(String id) throws CandidateNotFoundException{
        Candidates candidate=candidateRepository.findCandidateById(id);
        if (candidate ==null){
            throw  new CandidateNotFoundException(String.format("Candidate with ID: %s does not exist",candidate.getCandidateId()));
        }

        return map(candidate);
    }



    @Override
    public List<CandidatesResponse> getAll() {
        List<Candidates> candidates=candidateRepository.findAll();
        List<CandidatesResponse> candidatesResponseList=new ArrayList<>();

        for (Candidates savedCandidate:candidates){
            CandidatesResponse responses=new CandidatesResponse();
            responses.setId(savedCandidate.getCandidateId());
            responses.setFirstName(savedCandidate.getFirstName());
            responses.setLastName(savedCandidate.getLastName());
            responses.setPartyName(savedCandidate.getPartyName());
             candidatesResponseList.add(responses);

        }
        return candidatesResponseList;
    }
}
