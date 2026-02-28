package org.app.digitalVotingApp.utils;

import org.app.digitalVotingApp.data.dtos.requests.CandidateRegistrationRequest;
import org.app.digitalVotingApp.data.dtos.responses.CandidatesResponse;
import org.app.digitalVotingApp.model.Candidates;

import java.util.UUID;

public class CandidateUtils {

    public static CandidatesResponse map(Candidates savedCandidate) {
        CandidatesResponse responses=new CandidatesResponse();
        responses.setId(savedCandidate.getId());
        responses.setFirstName(savedCandidate.getFirstName());
        responses.setLastName(savedCandidate.getLastName());
        responses.setPartyName(savedCandidate.getPartyName());
        responses.setVoteCount(savedCandidate.getVoteCount());
         return  responses;
    }

    public static Candidates map(CandidateRegistrationRequest pollCreationRequest) {
        Candidates poll=new Candidates();
        poll.setId(UUID.randomUUID().toString());
        poll.setFirstName(pollCreationRequest.getFirstName());
        poll.setLastName(pollCreationRequest.getLastName());
        poll.setPartyName(pollCreationRequest.getPartyName());
        poll.setNin(pollCreationRequest.getNin());

        return poll;
    }
}
