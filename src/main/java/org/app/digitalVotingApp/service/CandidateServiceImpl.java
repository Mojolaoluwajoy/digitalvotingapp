package org.app.digitalVotingApp.service;

import org.app.digitalVotingApp.data.dtos.requests.CandidateRegistrationRequest;
import org.app.digitalVotingApp.data.dtos.responses.CandidatesResponse;
import org.app.digitalVotingApp.exceptions.CandidateAlreadyExistException;
import org.app.digitalVotingApp.exceptions.CandidateNotFoundException;
import org.app.digitalVotingApp.data.model.Candidates;
import org.app.digitalVotingApp.data.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
         Candidates savedPoll= candidateRepository.save(candidate);
         
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
       Optional< Candidates >candidate=candidateRepository.findByCandidateId(id);
        if (candidate.isEmpty()){
            throw  new CandidateNotFoundException(String.format("Candidate with this ID does not exist"));
        }

        return map(candidate.get());
    }



    @Override
    public List<CandidatesResponse> getAll() {
        List<Candidates> candidates=candidateRepository.findAll();
        List<CandidatesResponse> candidatesResponseList=new ArrayList<>();

        for (Candidates savedCandidate:candidates){
            CandidatesResponse responses=new CandidatesResponse();
            responses.setCandidateId(savedCandidate.getCandidateId());
            responses.setFirstName(savedCandidate.getFirstName());
            responses.setLastName(savedCandidate.getLastName());
            responses.setPartyName(savedCandidate.getPartyName());
             candidatesResponseList.add(responses);

        }
        return candidatesResponseList;
    }
}
