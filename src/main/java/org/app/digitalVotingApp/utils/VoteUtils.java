package org.app.digitalVotingApp.utils;

import org.app.digitalVotingApp.data.dtos.requests.CandidateRegistrationRequest;
import org.app.digitalVotingApp.data.dtos.requests.VoteRequest;
import org.app.digitalVotingApp.data.dtos.responses.CandidatesResponse;
import org.app.digitalVotingApp.data.dtos.responses.VoteRespose;
import org.app.digitalVotingApp.model.Candidates;
import org.app.digitalVotingApp.model.Vote;
import org.app.digitalVotingApp.model.Voters;

import java.util.UUID;

public class VoteUtils {

    public static VoteRespose map(Vote savedVote, Voters voter) {
        VoteRespose responses=new VoteRespose();
        responses.setVoteId(savedVote.getId());
        responses.setCandidateId(savedVote.getCandidateId());
        responses.setVoterId(voter.getId());
         return  responses;
    }

    public static Vote map(VoteRequest voteRequest) {
        Vote vote=new Vote();
        vote.setId(UUID.randomUUID().toString());
         vote.setVoterNin(voteRequest.getVoterNin());
         vote.setCandidateId(voteRequest.getCandidateId());

        return vote;
    }
}
