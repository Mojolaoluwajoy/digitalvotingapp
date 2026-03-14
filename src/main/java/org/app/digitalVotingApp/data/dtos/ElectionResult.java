package org.app.digitalVotingApp.data.dtos;

import lombok.Getter;
import lombok.Setter;
import org.app.digitalVotingApp.data.enums.ElectionStatusEnum;
import org.app.digitalVotingApp.data.model.Candidates;

import java.util.List;

@Setter
@Getter
public class ElectionResult {
   private   String ElectionId;
    private  int numberOfVoters;
    private int numberOfCandidates;
    private List<Candidates> candidatesList;
    public  Candidates winner;
    private ElectionStatusEnum status;
    private List<Candidates> tiedCandidates;

    public ElectionResult(ElectionStatusEnum status,Candidates winner,List<Candidates> candidatesList,int numberOfVoters,int numberOfCandidates){
        this.status=status;
        this.winner=winner;
        this.candidatesList=candidatesList;
        this.numberOfVoters=numberOfVoters;
        this.numberOfCandidates=numberOfCandidates;
    }
    public ElectionResult(ElectionStatusEnum status,List<Candidates> tiedCandidates,List<Candidates> candidatesList,int numberOfVoters,int numberOfCandidates){
        this.status=status;
        this.tiedCandidates=tiedCandidates;
        this.candidatesList=candidatesList;
        this.numberOfVoters=numberOfVoters;
        this.numberOfCandidates=numberOfCandidates;
    }


    public ElectionResult(ElectionStatusEnum status,List<Candidates> tiedCandidates){
        this.status=status;
        this.tiedCandidates=tiedCandidates;
    }
}
