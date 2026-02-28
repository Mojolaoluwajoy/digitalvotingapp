package org.app.digitalVotingApp.repository;

import org.app.digitalVotingApp.model.Candidates;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class CandidateRepository {
    private List <Candidates> candidatesList =new ArrayList<>();

    public Candidates createCandidate(Candidates candidates){
        candidatesList.add(candidates);
        return candidates;
    }
    public List<Candidates> findAll(){
        return candidatesList;
    }

    public Candidates findCandidateById(String id){
        for (Candidates candidate : candidatesList){
            if (candidate.getId().equals(id)){
                return  candidate;
            }
        }
        return  null;
    }

}
