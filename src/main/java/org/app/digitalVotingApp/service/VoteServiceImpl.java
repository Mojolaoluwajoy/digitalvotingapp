package org.app.digitalVotingApp.service;

import org.app.digitalVotingApp.data.dtos.requests.VoteRequest;
import org.app.digitalVotingApp.data.dtos.responses.VoteRespose;
import org.app.digitalVotingApp.exceptions.AlreadyVotedException;
import org.app.digitalVotingApp.exceptions.CandidateNotFoundException;
import org.app.digitalVotingApp.exceptions.VoterNotFoundException;
import org.app.digitalVotingApp.data.model.Candidates;
import org.app.digitalVotingApp.data.model.Vote;
import org.app.digitalVotingApp.data.model.Voters;
import org.app.digitalVotingApp.data.repository.CandidateRepository;
import org.app.digitalVotingApp.data.repository.VoteRepo;
import org.app.digitalVotingApp.data.repository.VotersRepository;
import org.app.digitalVotingApp.utils.VoteUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {
    @Autowired
private  VoteRepo voteRepo;
@Autowired
private CandidateRepository candidateRepository;
@Autowired
private  VotersRepository votersRepository;


 @Override
    public VoteRespose castVote(VoteRequest voteRequest) throws VoterNotFoundException,CandidateNotFoundException,AlreadyVotedException {
     String nin = voteRequest.getVoterNin();
     String candidateId = voteRequest.getCandidateId();
     if (voteRepo.findByVoterNin(nin).isPresent()){
         throw new AlreadyVotedException("The voter with this nin has already voted!") ;

     }

     Voters voters=votersRepository.findByNin(nin)
             .orElseThrow(()-> new VoterNotFoundException("There's no voter registered to this Nin"));

      Candidates candidates=candidateRepository.findByCandidateId(candidateId)
              .orElseThrow(()-> new CandidateNotFoundException("The candidate you're trying to vote for is not registered"));


        candidates.incrementVote();
        Vote votes = VoteUtils.map(voteRequest);

                    Vote savedVote = voteRepo.save(votes);
                      return VoteUtils.map(savedVote,voters);

    }


            @Override
            public List<VoteRespose> getAll () {
                List<Vote> votes = voteRepo.findAll();
                List<VoteRespose> voteResposeList = new ArrayList<>();

                for (Vote savedVote : votes) {
                    VoteRespose voteRespose = new VoteRespose();
                    voteRespose.setVotersId(savedVote.getVoteId());
                    voteRespose.setCandidateId(savedVote.getCandidateId());
                    voteResposeList.add(voteRespose);
                }
                return voteResposeList;
            }
        }
