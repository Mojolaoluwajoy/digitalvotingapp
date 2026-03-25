package org.app.digitalVotingApp.utils;

import org.app.digitalVotingApp.data.dtos.requests.VoteRequest;
import org.app.digitalVotingApp.data.dtos.responses.VoteRespose;
import org.app.digitalVotingApp.data.model.User;
import org.app.digitalVotingApp.data.model.Vote;

import java.util.UUID;

public class VoteUtils {

    public static VoteRespose map(Vote savedVote, User user) {
        VoteRespose responses=new VoteRespose();
        responses.setVoteId(savedVote.getPublicId());
        responses.setCandidateId(savedVote.getCandidateId());
        responses.setVotersId(user.getUserId());
         return  responses;
    }

    public static Vote map(VoteRequest voteRequest) {
        Vote vote=new Vote();
        vote.setPublicId(UUID.randomUUID().toString());
         vote.setVoterNin(voteRequest.getVoterNin());
         vote.setCandidateId(voteRequest.getCandidatePublicId());

        return vote;
    }
}
