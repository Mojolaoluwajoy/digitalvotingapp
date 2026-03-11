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
import org.app.digitalVotingApp.repository.CandidateRepository;
import org.app.digitalVotingApp.repository.VoteRepo;
import org.app.digitalVotingApp.repository.VotersRepository;
import org.app.digitalVotingApp.utils.VoteUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class VoteServiceImpl implements VoteService {
private final VoteRepo voteRepo=new VoteRepo();
@Autowired
private CandidateRepository candidateRepository;
@Autowired
private  VotersRepository votersRepository;


 @Override
    public VoteRespose castVote(VoteRequest voteRequest) throws VoterNotFoundException,CandidateNotFoundException,AlreadyVotedException {
     String nin = voteRequest.getVoterNin();
     String candidateId = voteRequest.getCandidateId();
     voteRepo.oneVotePerVoter(nin);

     Voters voters=votersRepository.findByNin(nin);
        Candidates candidates=candidateRepository.findCandidateById(candidateId);


        if (voters==null){
            throw new VoterNotFoundException("There's no voter registered to this Nin");
        }
        if (candidates==null) {
            throw new CandidateNotFoundException("The candidate you're trying to vote for is not registered");
        }
        candidates.incrementVote();
        Vote votes = VoteUtils.map(voteRequest);

                    Vote savedVote = voteRepo.save(votes);
                      return VoteUtils.map(savedVote,voters);

    }


            @Override
            public List<VoteRespose> getAll () {
                List<Vote> votes = voteRepo.getAll();
                List<VoteRespose> voteResposeList = new ArrayList<>();

                for (Vote savedVote : votes) {
                    VoteRespose voteRespose = new VoteRespose();
                    voteRespose.setVoterId(savedVote.getVoteId());
                    voteRespose.setCandidateId(savedVote.getCandidateId());
                    voteResposeList.add(voteRespose);
                }
                return voteResposeList;
            }
        }
