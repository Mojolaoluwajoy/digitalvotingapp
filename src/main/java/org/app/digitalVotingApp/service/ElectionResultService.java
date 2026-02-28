package org.app.digitalVotingApp.service;

import org.app.digitalVotingApp.enums.ElectionStatusEnum;
import org.app.digitalVotingApp.exceptions.EmptyCandidateListException;
import org.app.digitalVotingApp.model.Candidates;
import org.app.digitalVotingApp.model.ElectionResult;
import org.app.digitalVotingApp.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ElectionResultService {
    @Autowired
CandidateRepository candidateRepository;

   public ElectionResult getResult()throws EmptyCandidateListException {
        List<Candidates> candidates=candidateRepository.findAll();
        Candidates winner =findWinner(candidates);
        int totalVoters=countTotalVoters(candidates);
        List<Candidates> topCandidates=findTopCandidates(candidates, winner);
        return createResult(winner,topCandidates,candidates,totalVoters);
    }
    public Candidates findWinner(List <Candidates> candidates)throws EmptyCandidateListException {
      if (candidates.isEmpty()){
          throw new EmptyCandidateListException("No candidate found");
      }
        Candidates winner=candidates.get(0);
        for (Candidates candidate :candidates){
            if (candidate.getVoteCount()>winner.getVoteCount()){
                winner=candidate;
            }
        }
        return  winner;
    }
    private  int countTotalVoters(List<Candidates> candidates){
        int totalVoters=0;
        for (Candidates candidate:candidates){
            totalVoters+=candidate.getVoteCount();
        }
        return  totalVoters;
    }
    private  List <Candidates> findTopCandidates(List<Candidates> candidates,Candidates winner){
        List<Candidates> topCandidates=new ArrayList<>();
        Candidates toCompare=candidates.get(0);
        for (Candidates candidate:candidates) {

            if (candidate.getVoteCount() == toCompare.getVoteCount()) {
                topCandidates.add(candidate);
            }
        }
        return  topCandidates;
    }
    public  ElectionResult createResult(Candidates winner,List<Candidates> topCandidates,List<Candidates> candidates,int totalVoters){
      ElectionResult result;
      if (topCandidates.size()>1){
          result=new ElectionResult(ElectionStatusEnum.TIE,topCandidates,candidates,totalVoters,candidates.size());
      }else {
          result= new ElectionResult(ElectionStatusEnum.WINNER,winner,candidates,totalVoters,candidates.size());
      }
      return result;
    }

}
