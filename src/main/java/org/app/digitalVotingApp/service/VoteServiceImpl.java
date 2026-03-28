package org.app.digitalVotingApp.service;

import org.app.digitalVotingApp.data.dtos.requests.VoteRequest;
import org.app.digitalVotingApp.data.dtos.responses.VoteRespose;
import org.app.digitalVotingApp.data.model.User;
import org.app.digitalVotingApp.data.repository.UserRepository;
import org.app.digitalVotingApp.exceptions.AlreadyVotedException;
import org.app.digitalVotingApp.exceptions.CandidateNotFoundException;
import org.app.digitalVotingApp.exceptions.VoterNotFoundException;
import org.app.digitalVotingApp.data.model.CandidateProfile;
import org.app.digitalVotingApp.data.model.Vote;
import org.app.digitalVotingApp.data.repository.CandidateRepository;
import org.app.digitalVotingApp.data.repository.VoteRepository;
import org.app.digitalVotingApp.utils.VoteUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {
    @Autowired
private VoteRepository voteRepo;
@Autowired
private CandidateRepository candidateRepository;
@Autowired
    UserRepository userRepository;

 @Override
    public VoteRespose castVote(VoteRequest voteRequest) throws VoterNotFoundException,CandidateNotFoundException,AlreadyVotedException {
     String nin = voteRequest.getVoterNin();
     String candidateId = voteRequest.getCandidatePublicId();
     if (voteRepo.findByVoterNin(nin).isPresent()){
         throw new AlreadyVotedException("The voter with this nin has already voted!") ;

     }

    User user=userRepository.findByNin(nin)
             .orElseThrow(()-> new VoterNotFoundException("There's no user registered to this Nin"));

       userRepository.findByUserId(candidateId)
              .orElseThrow(()-> new CandidateNotFoundException("The candidate you're trying to vote for is not registered"));
CandidateProfile candidates=new CandidateProfile();
      candidates.incrementVote();
        Vote votes = VoteUtils.map(voteRequest);

                    Vote savedVote = voteRepo.save(votes);
                      return VoteUtils.map(savedVote,user);

    }


            @Override
            public List<VoteRespose> getAll () {
                List<Vote> votes = voteRepo.findAll();
                List<VoteRespose> voteResposeList = new ArrayList<>();

                for (Vote savedVote : votes) {
                    VoteRespose voteRespose = new VoteRespose();
                    voteRespose.setVotersId(savedVote.getPublicId());
                    voteRespose.setCandidateId(savedVote.getCandidateId());
                    voteResposeList.add(voteRespose);
                }
                return voteResposeList;
            }
        }
