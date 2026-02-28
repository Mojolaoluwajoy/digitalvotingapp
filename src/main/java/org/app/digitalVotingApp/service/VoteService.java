package org.app.digitalVotingApp.service;

import org.app.digitalVotingApp.data.dtos.requests.VoteRequest;
import org.app.digitalVotingApp.data.dtos.responses.VoteRespose;
import org.app.digitalVotingApp.exceptions.AlreadyVotedException;
import org.app.digitalVotingApp.exceptions.CandidateNotFoundException;
import org.app.digitalVotingApp.exceptions.VoterAlreadyExistException;
import org.app.digitalVotingApp.exceptions.VoterNotFoundException;
import org.app.digitalVotingApp.model.Candidates;
import org.app.digitalVotingApp.model.Vote;
import org.app.digitalVotingApp.model.Voters;

import java.util.List;

public interface VoteService {

     VoteRespose castVote(VoteRequest voteRequest)throws VoterNotFoundException, CandidateNotFoundException, AlreadyVotedException;

     List<VoteRespose> getAll();
}
