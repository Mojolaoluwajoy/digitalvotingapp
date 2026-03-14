package org.app.digitalVotingApp.utils;

import org.app.digitalVotingApp.data.dtos.requests.VoteRequest;
import org.app.digitalVotingApp.data.dtos.responses.VoteRespose;
import org.app.digitalVotingApp.data.model.Vote;
import org.app.digitalVotingApp.data.model.Voters;

import java.util.UUID;

public class VoteUtils {

    public static VoteRespose map(Vote savedVote, Voters voter) {
        VoteRespose responses=new VoteRespose();
        responses.setVoteId(savedVote.getVoteId());
        responses.setCandidateId(savedVote.getCandidateId());
        responses.setVotersId(voter.getVotersId());
         return  responses;
    }

    public static Vote map(VoteRequest voteRequest) {
        Vote vote=new Vote();
        vote.setVoteId(UUID.randomUUID().toString());
         vote.setVoterNin(voteRequest.getVoterNin());
         vote.setCandidateId(voteRequest.getCandidateId());

        return vote;
    }
}
