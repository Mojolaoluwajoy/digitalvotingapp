package org.app.digitalVotingApp.utils;

import org.app.digitalVotingApp.data.dtos.requests.CandidateRegistrationRequest;
import org.app.digitalVotingApp.data.dtos.responses.CandidatesResponse;
import org.app.digitalVotingApp.data.model.CandidateProfile;
import org.app.digitalVotingApp.data.model.User;

import java.util.UUID;

public class CandidateUtils {

    public static CandidatesResponse map(CandidateProfile savedCandidate) {
        CandidatesResponse responses=new CandidatesResponse();
User user=new User();
        responses.setFirstName(user.getFirstName());
        responses.setLastName(user.getLastName());
        responses.setPartyName(savedCandidate.getPartyName());
         return  responses;
    }

    public static CandidateProfile map(CandidateRegistrationRequest pollCreationRequest) {
        org.app.digitalVotingApp.data.model.CandidateProfile candidateProfile =new CandidateProfile();
User user=new User();
       candidateProfile.setUser(user);
candidateProfile.setPartyName(pollCreationRequest.getPartyName());
candidateProfile.setVoteCount(0);
        return candidateProfile;
    }
}
