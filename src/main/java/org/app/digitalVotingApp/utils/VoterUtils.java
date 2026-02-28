package org.app.digitalVotingApp.utils;

import org.app.digitalVotingApp.data.dtos.requests.VotersRegistrationRequest;
import org.app.digitalVotingApp.data.dtos.responses.VotersResponses;
import org.app.digitalVotingApp.model.Voters;

import java.util.UUID;

public class VoterUtils {

    public static VotersResponses map(Voters savedVoters){
        VotersResponses votersResponses =new VotersResponses();
        votersResponses.setId(savedVoters.getId());
        votersResponses.setFirstName(savedVoters.getFirstName());
        votersResponses.setLastName(savedVoters.getLastName());
        return votersResponses;
    }
    public static Voters map(VotersRegistrationRequest registrationRequest){
      Voters user=  new Voters();
        user.setId(UUID.randomUUID().toString());
        user.setFirstName(registrationRequest.getFirstName());
         user.setLastName(registrationRequest.getLastName());
         user.setNin(registrationRequest.getNin());
         return user;
    }
}
