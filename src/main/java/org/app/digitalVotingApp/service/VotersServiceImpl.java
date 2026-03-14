package org.app.digitalVotingApp.service;

import lombok.extern.slf4j.Slf4j;
import org.app.digitalVotingApp.data.dtos.requests.VotersRegistrationRequest;
import org.app.digitalVotingApp.data.dtos.responses.VotersResponses;
import org.app.digitalVotingApp.exceptions.VoterAlreadyExistException;
import org.app.digitalVotingApp.exceptions.VoterNotFoundException;
import org.app.digitalVotingApp.data.model.Voters;
import org.app.digitalVotingApp.data.repository.VotersRepository;
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
          if ( voterRepository.findByNin(nin).isPresent())
            throw  new VoterAlreadyExistException ("Voter with this nin  already exist" );

        Voters voters = map(registrationRequest);

        Voters savedVoters =voterRepository.save(voters);
        return map(savedVoters);
    }


        @Override
        public VotersResponses findById(String votersId)throws VoterNotFoundException{
            Voters voter=voterRepository.findByVotersId(votersId)
                    .orElseThrow(()-> new VoterNotFoundException("There's no voter registered to this Nin"));

       return map(voter);
        }


    @Override
    public List<VotersResponses> getAll() {
       List<Voters> voters=voterRepository.findAll();
       List <VotersResponses> votersList=new ArrayList<>();
       for (Voters savedVoter: voters){
           VotersResponses votersResponses =new VotersResponses();
           votersResponses.setVotersId(savedVoter.getVotersId());
           votersResponses.setFirstName(savedVoter.getFirstName());
           votersResponses.setLastName(savedVoter.getLastName());
           votersList.add(votersResponses);

       }
       return votersList;
    }


}
