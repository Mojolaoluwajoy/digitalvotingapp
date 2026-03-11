package org.app.digitalVotingApp.service;

import lombok.extern.slf4j.Slf4j;
import org.app.digitalVotingApp.data.dtos.requests.VotersRegistrationRequest;
import org.app.digitalVotingApp.data.dtos.responses.VotersResponses;
import org.app.digitalVotingApp.exceptions.VoterAlreadyExistException;
import org.app.digitalVotingApp.exceptions.VoterNotFoundException;
import org.app.digitalVotingApp.model.Voters;
import org.app.digitalVotingApp.repository.VotersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.app.digitalVotingApp.utils.VoterUtils.*;
@Slf4j
@Service
public class VotersServiceImpl implements VotersService{
   @Autowired
    private VotersRepository voterRepository ;

    @Override
    public VotersResponses register(VotersRegistrationRequest registrationRequest)
            throws VoterAlreadyExistException {
        String nin = registrationRequest.getNin();

       voterRepository.validateUserExistenceByNin(nin);
        Voters voters = map(registrationRequest);

        Voters savedVoters =voterRepository.addUser(voters);
        return map(savedVoters);
    }

    public Voters findByNin(String nin){
        return voterRepository.findByNin(nin);
    }


        @Override
        public VotersResponses findById(String id)throws VoterNotFoundException{
       Voters voter=voterRepository.findById(id);
       if (voter==null){
           throw new VoterNotFoundException(String.format("Voter with ID: %s does not exist",voter.getVotersId()));
       }
       return map(voter);
        }


    @Override
    public List<VotersResponses> getAll() {
       List<Voters> voters=voterRepository.findAll();
       List <VotersResponses> votersList=new ArrayList<>();
       for (Voters savedVoter: voters){
           VotersResponses votersResponses =new VotersResponses();
           votersResponses.setId(savedVoter.getVotersId());
           votersResponses.setFirstName(savedVoter.getFirstName());
           votersResponses.setLastName(savedVoter.getLastName());
           votersList.add(votersResponses);

       }
       return votersList;
    }


}
