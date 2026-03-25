package org.app.digitalVotingApp.service;

import org.app.digitalVotingApp.data.enums.ElectionStatusEnum;
import org.app.digitalVotingApp.exceptions.EmptyCandidateListException;
import org.app.digitalVotingApp.data.model.CandidateProfile;
import org.app.digitalVotingApp.data.dtos.ElectionResult;
import org.app.digitalVotingApp.data.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ElectionResultService {
    @Autowired
CandidateRepository candidateRepository;

   public ElectionResult getResult()throws EmptyCandidateListException {
        List<CandidateProfile> candidates=candidateRepository.findAll();
        CandidateProfile winner =findWinner(candidates);
        int totalVoters=countTotalVoters(candidates);
        List<CandidateProfile> topCandidates=findTopCandidates(candidates, winner);
        return createResult(winner,topCandidates,candidates,totalVoters);
    }
    public CandidateProfile findWinner(List <CandidateProfile> candidates)throws EmptyCandidateListException {
      if (candidates.isEmpty()){
          throw new EmptyCandidateListException("No candidate found");
      }
        CandidateProfile winner=candidates.get(0);
        for (CandidateProfile candidate :candidates){
            if (candidate.getVoteCount()>winner.getVoteCount()){
                winner=candidate;
            }
        }
        return  winner;
    }
    private  int countTotalVoters(List<CandidateProfile> candidates){
        int totalVoters=0;
        for (CandidateProfile candidate:candidates){
            totalVoters+=candidate.getVoteCount();
        }
        return  totalVoters;
    }
    private  List <CandidateProfile> findTopCandidates(List<CandidateProfile> candidates, CandidateProfile winner){
        List<CandidateProfile> topCandidates=new ArrayList<>();
        for (CandidateProfile candidate:candidates) {

            if (candidate.getVoteCount() == winner.getVoteCount()) {
                topCandidates.add(candidate);
            }
        }
        return  topCandidates;
    }
    public  ElectionResult createResult(CandidateProfile winner, List<CandidateProfile> topCandidates, List<CandidateProfile> candidates, int totalVoters){
      ElectionResult result;
      if (topCandidates.size()>1){
          result=new ElectionResult(ElectionStatusEnum.TIE,topCandidates,candidates,totalVoters,candidates.size());
      }else {
          result= new ElectionResult(ElectionStatusEnum.WINNER,winner,candidates,totalVoters,candidates.size());
      }
      return result;
    }

}
